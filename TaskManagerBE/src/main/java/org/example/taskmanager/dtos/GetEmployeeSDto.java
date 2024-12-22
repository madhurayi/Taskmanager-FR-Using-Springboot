package org.example.taskmanager.dtos;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetEmployeeSDto {
    Long id;
    String empId;
    String empName;
}
