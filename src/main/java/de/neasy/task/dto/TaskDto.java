package de.neasy.task.dto;

import de.neasy.task.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {
    private long id;
    private String description;
    private String name;
    private long createdById;
    private String createdByName;
    private long assignToId;
    private String status;

    /*@ManyToOne
    private List<User> user;*/
    /*@ManyToOne
    private User user;
    */
    /*@OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name="assign_to_id", referencedColumnName="assignToId")
    private List<User> user;*/
}

