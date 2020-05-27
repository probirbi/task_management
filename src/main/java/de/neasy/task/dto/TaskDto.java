package de.neasy.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {
    private long id;
    private String description;
    private String name;
    private long createdById;
    private long assignToId;
    private String status;
}

