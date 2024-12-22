package org.example.taskmanager.controllers;

import org.example.taskmanager.dtos.CreateEmployeeDto;
import org.example.taskmanager.models.Employee;
import org.example.taskmanager.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
     EmployeeService employeeService;
    public EmployeeController( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?>  createEmployee( @RequestBody  CreateEmployeeDto createEmployeeDto){
        System.out.println(createEmployeeDto.getClass());
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(createEmployeeDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }
}
