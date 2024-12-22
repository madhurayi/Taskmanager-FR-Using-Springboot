package org.example.taskmanager.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTasksDto {
    Long id;
    String tasknumber;
    String title;
    String description;
    String status;
    String priority;
    Date duedate;
    String empId;

}
