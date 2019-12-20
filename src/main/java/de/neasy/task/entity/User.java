package de.neasy.task.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

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
    private String date;
    private String email;
}
