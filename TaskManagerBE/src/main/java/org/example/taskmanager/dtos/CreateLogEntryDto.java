package org.example.taskmanager.dtos;

import lombok.*;
import org.example.taskmanager.models.Task;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateLogEntryDto {
     Date logDate;
     String logNote;
     String logTime;
     String logEntryUserId;
     String taskNumber;
}
