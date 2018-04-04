package com.cegeka.evaluations.domain.populateDB;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeMapper;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.roleservices.ASFLeadRoleService;
import com.cegeka.evaluations.domain.roleservices.EmployeeRoleService;
import com.cegeka.evaluations.domain.roleservices.PeopleManagerRoleService;
import com.cegeka.evaluations.domain.roleservices.PortfolioManagerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
class DBpopulator {

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @Autowired
    private PeopleManagerRoleService peopleManagerRoleService;

    @Autowired
    private PortfolioManagerRoleService portfolioManagerRoleService;

    @Autowired
    private ASFLeadRoleService asfLeadRoleService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    void populateDB(List<LdapEmployeeDTO> employeesFromLDAP) {
        populateDBWithLDAPUsers(employeesFromLDAP);
        peopleManagerRoleService.setManagerWithPeopleManagerRole(employeesFromLDAP);
        peopleManagerRoleService.revokePeopleManagerRole();
        portfolioManagerRoleService.setPortfolioManagerBasedOnManagerWhoHasManagers();
        portfolioManagerRoleService.setPortfolioManagerBasedOnAttributeFieldInLdap(employeesFromLDAP);
        asfLeadRoleService.setRoleForASFLeads();
        employeeRoleService.setRoleToEmployee(employeesFromLDAP);
    }

    private void populateDBWithLDAPUsers(List<LdapEmployeeDTO> employeesFromLDAP) {
        for (LdapEmployeeDTO employeeDTO : employeesFromLDAP) {
            if (!employeeAlreadyExistsInDB(employeeDTO)) {
                Employee employee = employeeMapper.mapLdapEmployeeDtoToEmployee(employeeDTO);
                employeeRepository.save(employee);
            }
        }
    }

    private boolean employeeAlreadyExistsInDB(LdapEmployeeDTO employeeDTO) {
        return employeeRepository.findByUserName(employeeDTO.getUserName()) != null;
    }
}