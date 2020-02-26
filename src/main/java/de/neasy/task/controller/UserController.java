package de.neasy.task.controller;

import de.neasy.task.dto.TaskDto;
import de.neasy.task.entity.Task;
import de.neasy.task.repository.TaskRepository;
import de.neasy.task.repository.UserRepository;
import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        System.out.println("Login page");
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(UserDto userDto, HttpSession session) {

        String userEmail = userDto.getEmail();
        String userPassword = userDto.getPassword();

        if (userEmail.isEmpty() == false && userPassword.isEmpty() == false) {
            User user = userRepository.findByEmailAndPassword(userEmail, userPassword);

            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:list";
                //return "redirect:task";
            } else {
                return "login";
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:login";
    }

    @GetMapping("/profile")
    public String profile() {
        System.out.println("Profile page");
        return "profile";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String saveData(UserDto userdto) {

        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setPassword(userdto.getPassword());
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());

        userRepository.save(user);

        return "redirect:list";
    }

    @GetMapping("list")
    public String getUserList(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        return "list";
    }

    @RequestMapping("/edituser/{id}")
    public String edit(@PathVariable Long id, Model model) {

        User user = userRepository.findById(id);
        model.addAttribute("user", user);

        return "edit";
    }

    @PostMapping("/editsave")
    public String editSave(UserDto userdto) {

        User user = userRepository.findById(userdto.getId());

        user.setId(userdto.getId());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());

        userRepository.save(user);

        return "redirect:list";
    }

/*    @GetMapping("/deleteuser/{id}")
    public String delete(@PathVariable Long id) {

        User user = new User();
        user.setId(id);

        userRepository.delete(user);
        return "redirect:/user/list";
    }*/

}
