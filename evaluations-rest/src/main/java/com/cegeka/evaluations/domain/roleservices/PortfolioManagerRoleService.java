package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Component
public class PortfolioManagerRoleService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void setPortfolioManagerBasedOnAttributeFieldInLdap(List<LdapEmployeeDTO> employeesFromLDAP) {
        for (LdapEmployeeDTO employeeDTO : employeesFromLDAP) {
            Employee employeeOpt = employeeRepository.findByUserName(employeeDTO.getUserName());

            if (employeeOpt != null) {
                if (employeeDTO.isPortfolioManager()) {
                    employeeOpt.setRole(Role.PortfolioManager);
                }
            }
        }
    }

    @Transactional
    public void setPortfolioManagerBasedOnManagerWhoHasManagers() {
        StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .map(Employee::getManager)
                .filter(Objects::nonNull)
                .map(Employee::getManager)
                .filter(Objects::nonNull)
                .distinct()
                .forEach(employee -> employee.setRole(Role.PortfolioManager));
    }
}
