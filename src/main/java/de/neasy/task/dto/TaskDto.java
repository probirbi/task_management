package de.neasy.task.dto;

import lombok.Data;

@Data
public class TaskDto {
    private long id;
    private String description;
    private String name;
    private long createdById;
    private long assignToId;
}

