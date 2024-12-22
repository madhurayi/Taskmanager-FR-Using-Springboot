package org.example.taskmanager.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetLogEntriesDto {
    Long id;
    String taskNumber;
    Date logDate;
    String logTime;
    String logNote;
    String logUser;

}
