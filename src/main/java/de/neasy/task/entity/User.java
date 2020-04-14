package de.neasy.task.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String email;
    private String date;
    private String filePath;

  /*  @Override
    public String toString() {
        return String.format("User[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }*/
}
