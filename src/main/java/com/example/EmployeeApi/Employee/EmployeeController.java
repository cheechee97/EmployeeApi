package com.example.EmployeeApi.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController implements EmployeeDAO{

//    private static final List<Employee> EMPLOYEES = Arrays.asList(
//            new Employee(1, "Scarlett", 20, "scarlett20@gmail.com" ),
//            new Employee(2, "Wilson", 25, "wilson25@gmail.com" ),
//            new Employee(3, "Jeremy", 27, "jeremy27@gmail.com" )
//    );

    private JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // rowMapper function
    RowMapper<Employee> rowMapper = (rs, rowNum) -> { // resultSet and row Number

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employeeId"));
        employee.setEmployeeName(rs.getString("employeeName"));
        employee.setEmployeeAge(rs.getInt("age"));
        employee.setEmployeeEMail(rs.getString("email_address"));

        return (Employee) employee;

    };


    @Override
    @GetMapping
    public List<Employee> getAllEmployee() {
        String sql = "select * from employees";
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    @GetMapping(path = "{id}")
    public Optional<Employee> getEmployeeById( @PathVariable("id") int employeeId) {
        System.out.println("--------------------------------");
        System.out.println("Employee Id: " + Long.toString(employeeId));
        System.out.println("--------------------------------");
        String sql = "select employeeName, age, email_address from dbo.employees where employeeId = ?";
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(sql,new Object[] {employeeId}, rowMapper);
        } catch (DataAccessException ex) {
            System.out.println(ex);
            log.info("Employee not found: " + employeeId);
        }

        return Optional.ofNullable(employee);
    }

    @Override
    public void addEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(Employee employee, int employeeId) {

    }

    @Override
    public void deleteEmployee(int employeeId) {

    }
}
