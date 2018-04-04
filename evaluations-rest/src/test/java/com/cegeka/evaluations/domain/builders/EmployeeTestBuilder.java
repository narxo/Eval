package com.cegeka.evaluations.domain.builders;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.Role;

public class EmployeeTestBuilder {

    private String firstName = "default firstName";
    private String lastName = "default lastName";
    private String userName = "default userName";
    private Role role = Role.Employee;
    private Employee manager = null;

    public Employee build() {
        Employee employee = new Employee(firstName, lastName, userName, role);
        employee.setManager(manager);
        return employee;
    }

    public static EmployeeTestBuilder anEmployee(){
        return new EmployeeTestBuilder()
                .withFirstName("firstname")
                .withLastName("lastname")
                .withUserName("username");
    }

    public static EmployeeTestBuilder david() {
        return anEmployee()
                .withFirstName("David")
                .withLastName("Scheers")
                .withUserName("davids");
    }

    public static EmployeeTestBuilder xan() {
        return anEmployee()
                .withFirstName("Xan")
                .withLastName("Vranckaert")
                .withUserName("xanV");
    }

    private EmployeeTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    private EmployeeTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeTestBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public EmployeeTestBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public EmployeeTestBuilder withManager(Employee manager) {
        this.manager = manager;
        return this;
    }
}
