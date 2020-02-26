package de.neasy.task.controller;

import de.neasy.task.dto.TaskDto;
import de.neasy.task.entity.Task;
import de.neasy.task.entity.User;
import de.neasy.task.repository.TaskRepository;
import de.neasy.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/create")
    public String task(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        return "task";
    }

    @PostMapping("/save")
    public String saveTask(TaskDto taskDto, HttpSession httpSession) {

        User loggedInUser = (User) httpSession.getAttribute("user");

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCreatedById(loggedInUser.getId());
        task.setAssignToId(taskDto.getAssignToId());

        taskRepository.save(task);

        return "redirect:create";
    }

    @GetMapping("/assignList")
    public String assignList(Model model, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("user");
        //List<Task> assignList = taskRepository.findAll();
        List<Task> assignList = taskRepository.getAllByAssignToId(loggedInUser.getId());
        model.addAttribute("lists", assignList);
        System.out.println("ShowList"+assignList);
        return "assignList";
    }

}
