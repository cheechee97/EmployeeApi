package com.example.EmployeeApi.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();
    Optional<Employee> getEmployeeById(int employeeId);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee, int employeeId);
    void deleteEmployee(int employeeId);
}
