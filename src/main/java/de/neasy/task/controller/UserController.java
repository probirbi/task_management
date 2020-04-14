package de.neasy.task.controller;

import de.neasy.task.repository.UserRepository;
import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserRepository userRepository;
   // private final Path rootLocation = Paths.get("./static");

    // @Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        System.out.println("Login page");
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(UserDto userDto, HttpSession session) {

        String userEmail = userDto.getEmail();
        String userPassword = userDto.getPassword();

        String encryptPassword = encryptPassword(userPassword);
        //String decryptPassword = decryptPassword(userPassword);

        if (userEmail.isEmpty() == false && userPassword.isEmpty() == false) {
            User user = userRepository.findByEmailAndPassword(userEmail, encryptPassword);

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

    private String encryptPassword(String password) {

        if (password.length() >= 1) {

            String encryptedString = "";

            for (int i = 0; i < password.length(); i++) {
                char character = password.charAt(i);
                int ascii = character;
                ascii--;
                char convertedCharacter = (char) ascii;
                encryptedString = encryptedString + convertedCharacter;
            }

            return encryptedString;
        }

        return "";
    }

    private String decryptPassword(String password) {

        if (password.length() >= 1) {

            String decryptedString = "";

            for (int i = 0; i < password.length(); i++) {
                char character = password.charAt(i);
                int ascii = character;
                ascii++;
                char convertedCharacter = (char) ascii;
                decryptedString = decryptedString + convertedCharacter;
            }

            return decryptedString;
        }

        return "";
    }

    @PostMapping("/register")
    public String saveData(UserDto userdto) {

        MultipartFile picture = userdto.getProfilepicture();
        String fileNewName = File.separator + System.currentTimeMillis() + StringUtils.cleanPath(picture.getOriginalFilename());
        String pathDirectory = "." + File.separator + "static" + fileNewName;

        try {
            Path copyLocation = Paths.get(pathDirectory);
            Files.copy(picture.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {

        }

        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());

        String encryptedPassword = encryptPassword(userdto.getPassword());

        user.setPassword(encryptedPassword);
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());
        user.setFilePath(fileNewName);

        userRepository.save(user);
        return "redirect:list";
    }

    @GetMapping("list")
    public String getUserList(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);

        return "user/list";
    }

    @RequestMapping("/edituser/{id}")
    public String edit(@PathVariable Long id, Model model) {

        User user = userRepository.findById(id);
        model.addAttribute("user", user);

        return "user/edit";
    }

    /*public String imageUpload(String a){
        UserDto userDto=new UserDto();
        MultipartFile picture=userDto.getProfilepicture();
        String fileNewName = File.separator + System.currentTimeMillis() + StringUtils.cleanPath(picture.getOriginalFilename());
        String pathDirectory = "." + File.separator + "static" + fileNewName;

        try {
            Path copyLocation = Paths.get(pathDirectory);
            Files.copy(picture.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {

        }
        return "";
    }*/

    @PostMapping("/editsave")
    public String editSave(UserDto userdto) {

        MultipartFile picture = userdto.getProfilepicture();
        String fileNewName = File.separator + System.currentTimeMillis() + StringUtils.cleanPath(picture.getOriginalFilename());
        String pathDirectory = "." + File.separator + "static" + fileNewName;

        try {
            Path copyLocation = Paths.get(pathDirectory);
            Files.copy(picture.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {

        }

        User user = userRepository.findById(userdto.getId());

        try {
            String realDirectory = "." + File.separator + "static" + user.getFilePath();
            File file = new File(realDirectory);
            file.delete();
        } catch (Exception ex) {

        }

        user.setId(userdto.getId());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());
        user.setFilePath(fileNewName);

        userRepository.save(user);

        return "redirect:list";
    }


}
