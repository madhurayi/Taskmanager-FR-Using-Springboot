package org.example.taskmanager.services;

import org.example.taskmanager.dtos.CreateEmployeeDto;
import org.example.taskmanager.dtos.GetEmployeeSDto;
import org.example.taskmanager.models.Employee;
import org.example.taskmanager.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee saveEmployee(CreateEmployeeDto employee) {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()) {
            Employee newEmployee = new Employee();
            newEmployee.setEmpName(employee.getEmpName());
//            System.out.println(newEmployee.getEmpName()+"emp name");
            newEmployee.setEmpId("P-1000");
            employeeRepository.save(newEmployee);
            return newEmployee;
        }
        Employee emp=employees.get(employees.size()-1);
        String[] maxEmpIdArr=emp.getEmpId().split("-");
        int num= Integer.parseInt(maxEmpIdArr[maxEmpIdArr.length-1])+1;
        String maxEmpID="P-"+(num);
        Employee newEmployee = new Employee();
//        System.out.println(employee.getEmpName()+"emp name");
        newEmployee.setEmpName(employee.getEmpName());
        newEmployee.setEmpId(maxEmpID);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    @Override
    public List<GetEmployeeSDto> getAllEmployees() {
        List<GetEmployeeSDto> empList=new ArrayList<>();
        for(Employee emp:employeeRepository.findAll()) {
            GetEmployeeSDto empDto=new GetEmployeeSDto();
            empDto.setEmpId(emp.getEmpId());
            empDto.setEmpName(emp.getEmpName());
            empDto.setId(emp.getId());
            empList.add(empDto);
        }
        return empList;
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

}
