package org.example.taskmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends BaseModel{
    @Column
    String empName;
    @Column(unique=true)
    String empId;
}
