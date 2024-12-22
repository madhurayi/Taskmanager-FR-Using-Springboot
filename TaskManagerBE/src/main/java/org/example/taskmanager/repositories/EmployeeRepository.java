package org.example.taskmanager.repositories;

import org.example.taskmanager.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee save(Employee employee);
    public List<Employee> findAll();
    public Optional<Employee> findByEmpId(String empId);
}
