package org.example.taskmanager.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskDto {
    String title;
    Date duedate;
    String empId;
    String priority;
}
