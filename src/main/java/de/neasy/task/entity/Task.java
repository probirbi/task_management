package de.neasy.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    /*Created relational database, createdBy and assignTo*/
    @OneToOne
    private User createdBy;

    @OneToOne
    private User assignTo;

    private String status;
}
