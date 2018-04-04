package com.cegeka.evaluations.domain.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoMapper {

    public EmployeeDTO mapEmployeeToLoggedInEmployeeDTO(Employee employee) {
        if (employee.getRole() != Role.ASFLead && (employee.getManager() != null)) {
            return new EmployeeDTO(employee.getFirstName(),
                    employee.getLastName(),
                    employee.getUserName(),
                    employee.getRole(),
                    employee.getManager().getUserName());
        } else {
            return new EmployeeDTO(employee.getFirstName(),
                    employee.getLastName(),
                    employee.getUserName(),
                    employee.getRole());
        }
    }
}
