package org.example.taskmanager.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    String title;
    String description;
    String priority;
    Date duedate;
    String assignedto;
}
