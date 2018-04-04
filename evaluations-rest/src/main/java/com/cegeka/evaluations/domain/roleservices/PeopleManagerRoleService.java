package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class PeopleManagerRoleService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void setManagerWithPeopleManagerRole(List<LdapEmployeeDTO> employeesFromLDAP) {
        for (LdapEmployeeDTO employeeDTO : employeesFromLDAP) {

            Employee employee = employeeRepository.findByUserName(employeeDTO.getUserName());

            Optional<LdapEmployeeDTO> managerDTOOpt = employeesFromLDAP.stream()
                    .filter(ldapEmployee -> employeeDTO.getManagerCN().equals(ldapEmployee.getCN()))
                    .findFirst();

            if (employee != null && managerDTOOpt.isPresent()) {
                Employee manager = employeeRepository.findByUserName(managerDTOOpt.get().getUserName());
                employee.setManager(manager);
                manager.setRole(Role.PeopleManager);
            }
        }
    }

    @Transactional
    public void revokePeopleManagerRole() {
        List<Employee> validPeopleManagers = employeeRepository.findByManagerOfAnotherEmployee();
        employeeRepository
                .findByRolePeopleManager()
                .stream()
                .filter(employee -> !validPeopleManagers.contains(employee))
                .forEach(employee -> employee.setRole(Role.Employee));
    }
}
