package de.neasy.task.dto;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String date;
    private String email;

}
