package de.neasy.task.dto;

import de.neasy.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String date;
    private String email;
    private MultipartFile profilepicture;

    public UserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //private Task tasks;
}
