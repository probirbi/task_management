package de.neasy.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String date;
    private String email;
    private MultipartFile profilepicture;
}
