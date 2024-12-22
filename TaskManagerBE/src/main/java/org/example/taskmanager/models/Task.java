package org.example.taskmanager.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends BaseModel{
    private String taskNumber;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date taskDueDate;
    @ManyToOne
    private Employee employee;
    }
