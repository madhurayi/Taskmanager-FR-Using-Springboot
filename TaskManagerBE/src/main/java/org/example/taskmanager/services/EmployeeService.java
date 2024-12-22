package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateEmployeeDto;
import org.example.taskmanager.dtos.GetEmployeeSDto;
import org.example.taskmanager.models.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(CreateEmployeeDto createEmployeeDto);
    public List<GetEmployeeSDto> getAllEmployees();
    public Employee getEmployee(Long id);

}
