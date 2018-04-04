package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmployeeRoleService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void setRoleToEmployee(List<LdapEmployeeDTO> employees) {
        for (LdapEmployeeDTO employeeDTO : employees) {
            Employee employee = employeeRepository.findByUserName(employeeDTO.getUserName());
            if (employee.getRole() == null) {
                employee.setRole(Role.Employee);
                employeeRepository.save(employee);
            }
        }
    }
}
