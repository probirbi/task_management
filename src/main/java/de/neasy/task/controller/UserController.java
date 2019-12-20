package de.neasy.task.controller;

import de.neasy.task.repository.UserRepository;
import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user/")
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
        System.out.println("LoginPost");
        String userEmail = userDto.getEmail();
        String userPassword = userDto.getPassword();
        if (userEmail.isEmpty() == false && userPassword.isEmpty() == false) {
            User user = userRepository.findByEmailAndPassword(userEmail, userPassword);

            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:profile";
            } else {
                return "login";
            }
        }
        return "login";
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

    @GetMapping("/list")
    public String getUserList(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        return "list";
    }

}
