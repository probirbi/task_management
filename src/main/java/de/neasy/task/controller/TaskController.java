package de.neasy.task.controller;

import de.neasy.task.dto.TaskDto;
import de.neasy.task.entity.Task;
import de.neasy.task.entity.User;
import de.neasy.task.repository.TaskRepository;
import de.neasy.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/create")
    public String task(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        return "task";
    }

    @PostMapping("/save")
    public String saveTask(TaskDto taskDto, HttpSession httpSession) {

        User loggedInUser = (User) httpSession.getAttribute("user");

        /*Relational database created with 2 tables, task table brings data from User table virtually*/
        User createdByUserObj = userRepository.findById(loggedInUser.getId());
        User assignedToUserObject = userRepository.findById(taskDto.getAssignToId());

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());

        task.setCreatedBy(createdByUserObj);
        task.setAssignTo(assignedToUserObject);
        taskRepository.save(task);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(createdByUserObj.getEmail());
        mail.setTo(assignedToUserObject.getEmail());
        mail.setSubject(taskDto.getName());
        mail.setText(taskDto.getDescription());
        /*mail.setSubject("Testing EMail API for Task Management Systems");
        mail.setText("Hurray ! I have done email notification for task ...");*/
        javaMailSender.send(mail);

        return "redirect:create";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Task task = taskRepository.findById(id);
        model.addAttribute("task", task);

        return "edittask";
    }

    @PostMapping("/editsave")
    public String editSave(TaskDto taskDto) {

        Task task = taskRepository.findById(taskDto.getId());

        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());

        taskRepository.save(task);

        return "redirect:createdTask";
    }

    @GetMapping("/createdTask")
    public String createdTask(Model model, HttpSession httpSession) {

        User loggedInUser = (User) httpSession.getAttribute("user");
        List<Task> createdTaskList = taskRepository.getAllByCreatedById(loggedInUser.getId());

        model.addAttribute("lists", createdTaskList);
        return "createdtask";
    }

    @GetMapping("/assignList")
    public String assignList(Model model, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("user");
        List<Task> assignList = taskRepository.getAllByAssignToId(loggedInUser.getId());

        model.addAttribute("lists", assignList);
        return "assignlist";
    }

    @PostMapping("/changestatus")
    @ResponseBody
    public String taskStatus(@RequestBody TaskDto taskDto) {

        Task task = taskRepository.findById(taskDto.getId());
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);

        return "success";
    }

}
