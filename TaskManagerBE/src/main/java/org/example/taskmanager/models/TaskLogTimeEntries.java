package org.example.taskmanager.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskLogTimeEntries extends BaseModel {
    @ManyToOne
    private Task task;
    private Date logDate;
    private String logNote;
    private String logTime;
    @ManyToOne
    private Employee logEntryUser;
}
