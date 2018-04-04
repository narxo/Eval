package com.cegeka.evaluations.domain.employee;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee mapLdapEmployeeDtoToEmployee(LdapEmployeeDTO ldapEmployeeDTO){
        Employee employee = new Employee(ldapEmployeeDTO.getFirstName(),
                ldapEmployeeDTO.getLastName(),ldapEmployeeDTO.getUserName(), Role.Employee);
        return employee;
    }

}
