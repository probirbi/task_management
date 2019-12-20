package de.neasy.task.dto;

import de.neasy.task.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserListDto {
    private List<User> users;

    public void addUser(User user){
        this.users.add(user);
    }

    public void addAttribute(String users, List<User> all) {
    }
}
