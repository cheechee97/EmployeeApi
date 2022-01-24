package com.example.EmployeeApi.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController implements EmployeeDAO{

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
        String sql = "select employeeId, employeeName, age, email_address from employees where employeeId = ?";
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(sql,new Object[] {employeeId}, rowMapper);
        } catch (DataAccessException ex) {
            log.error(ex.toString());
            log.info("Employee not found: " + employeeId);
        }

        return Optional.ofNullable(employee);
    }

    @Override
    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
        String sql = "INSERT INTO employees(employeeName, age, email_address) values (?,?,?)";
        int insert = jdbcTemplate.update(sql, employee.getEmployeeName(), employee.getEmployeeAge(), employee.getEmployeeEMail());
        if (insert == 1) {
            log.info("New employee added !");
        }
    }

    @Override
    @PutMapping("/update/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable("id") int employeeId) {
        String sql = "update employees set employeeName = ?, age = ? , email_address = ? where employeeId = ?";
        int update = jdbcTemplate.update(sql, employee.getEmployeeName(), employee.getEmployeeAge(), employee.getEmployeeEMail(), employeeId);
        if (update == 1) {
            log.info("Employee ( ID : " + Integer.toString(employeeId) + " )  has updated !");
        }
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") int employeeId) {
        String sql = "delete from employees where employeeId = ?";
        int delete = jdbcTemplate.update(sql, employeeId);
        if (delete == 1) {
            log.info("Employee ( ID: " + Integer.toString(employeeId) + " ) has deleted !");
        } 
    }
}
