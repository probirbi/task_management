package de.neasy.task.controller;

import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.User;
import de.neasy.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class TaskApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user/getAll")
    public List<User> getAllUerList(){
        return userRepository.findAll();
    }

    @GetMapping("/api/user/get/{id}")
    public User getAllUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/api/task/get/{id}")
    public User getAllTaskById(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/api/user/create")
    public User createUser(@RequestBody UserDto userdto) {

        User user=new User();
        user.setId(userdto.getId());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setPassword(userdto.getPassword());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());
        userRepository.save(user);

        return user;
    }


    @GetMapping("/api/deleteuser/{id}")
    public String delete(@PathVariable Long id) {

        User user = userRepository.findById(id);
        System.out.println("(Service Side) Deleting employee: " + id);
        userRepository.delete(user);

        return "success";
    }

}
