package de.neasy.task.controller;

import de.neasy.task.dto.TaskDto;
import de.neasy.task.dto.UserDto;
import de.neasy.task.entity.Task;
import de.neasy.task.entity.User;
import de.neasy.task.repository.UserRepository;
import de.neasy.task.repository.TaskRepository;
import de.neasy.task.util.WriteDataToCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/save")
    public User saveDataByAjax(@RequestBody UserDto userdto) {

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
        user.setPassword(userdto.getPassword());
        user.setAddress(userdto.getAddress());
        user.setDate(userdto.getDate());
        user.setEmail(userdto.getEmail());

        user.setFilePath(pathDirectory);
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

    @GetMapping("/download/users.csv")
    public void downloadCSV(HttpServletResponse response) throws IOException

    {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=users.csv");

        List<User> userList = userRepository.findAll();
        WriteDataToCSV.writeObjectToCSV(response.getWriter(), userList);
    }
}
