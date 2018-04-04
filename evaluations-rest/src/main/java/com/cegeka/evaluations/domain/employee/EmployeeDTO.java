package com.cegeka.evaluations.domain.employee;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private Role role;
    private String managerUserName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, String userName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
    }

    public EmployeeDTO(String firstName, String lastName, String userName, Role role, String managerUserName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
        this.managerUserName = managerUserName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }

    public String getManagerUserName() {
        return managerUserName;
    }
}
