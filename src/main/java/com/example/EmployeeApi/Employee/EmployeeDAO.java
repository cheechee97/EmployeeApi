package com.example.EmployeeApi.Employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {


    List<Employee> getAllEmployee();


    Optional<Employee> getEmployeeById(@PathVariable("employeeId") int employeeId);

    void addEmployeeById(Employee employee);
    void updateEmployeeById(Employee employee, int employeeId);
    void deleteEmployeeById(int employeeId);
}
