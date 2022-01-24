package com.example.EmployeeApi.Employee;

public class Employee {

    private Integer employeeId;
    private String employeeName;
    private Integer employeeAge;
    private String employeeEMail;


    public Employee(Integer employeeId, String employeeName, Integer employeeAge, String employeeEMail) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeEMail = employeeEMail;
    }

    public Employee(String employeeName, Integer employeeAge, String employeeEMail) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeEMail = employeeEMail;
    }

    public Employee() {

    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public String getEmployeeEMail() {
        return employeeEMail;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public void setEmployeeEMail(String employeeEMail) {
        this.employeeEMail = employeeEMail;
    }
}
