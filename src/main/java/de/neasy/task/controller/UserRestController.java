package de.neasy.task.controller;

import de.neasy.task.dto.TaskDto;
import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.Task;
import de.neasy.task.entity.User;
import de.neasy.task.repository.UserRepository;
import de.neasy.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/save")
    public User saveDataByAjax(@RequestBody UserDto userdto) {

        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setPassword(userdto.getPassword());
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());

        user = userRepository.saveAndFlush(user);

        return user;
    }

    @GetMapping("/deleteuser/{id}")
    public String delete(@PathVariable Long id) {

        User user = userRepository.findById(id);
        System.out.println("(Service Side) Deleting employee: " + id);
        userRepository.delete(user);

        return "success";
    }

    @GetMapping("/deletetask/{id}")
    public String deleteTask(@PathVariable Long id) {

        Task task = taskRepository.findById(id);
        System.out.println("(Service Side) Deleting Task Id: " + id);
        taskRepository.delete(task);

        return "success";
    }
}
