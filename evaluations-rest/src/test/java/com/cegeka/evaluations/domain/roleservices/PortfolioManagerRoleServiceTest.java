package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.populateDB.LDAPEmployeeDTOTestBuilder;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import com.cegeka.evaluations.springConfiguration.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringIntegrationTest
public class PortfolioManagerRoleServiceTest {

    @Autowired
    private PortfolioManagerRoleService portfolioManagerRoleService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private LdapEmployeeDTO employeeDTO1, employeeDTO2, employeeDTO3, peopleManagerDTO1, portfolioManagerDTO1, aSFLeadDTO1;

    private Employee employee1, employee2, employee3, peopleManager1, portfolioManager1,aSFLead1;

    private List<LdapEmployeeDTO> ldapEmployeeDTOList;

    @Before
    public void setUp() throws Exception {
        employeeDTO1 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Domien").withCN("Domien").withManagerCN("Seppe").build();
        employeeDTO2 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Gerri").withCN("Gerri").withManagerCN("Seppe").build();
        employeeDTO3 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Jeroen").withCN("Jeroen").withManagerCN("Seppe").build();
        peopleManagerDTO1 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Seppe").withCN("Seppe").withManagerCN("Jos").build();
        portfolioManagerDTO1 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Jos").withCN("Jos").withManagerCN("Jan").build();
        aSFLeadDTO1 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Jan").withCN("Jan").withManagerCN("").build();

        portfolioManagerDTO1.setIsPortfolioManager(true);

        ldapEmployeeDTOList = Arrays.asList(employeeDTO1, employeeDTO2, employeeDTO3, peopleManagerDTO1, portfolioManagerDTO1,aSFLeadDTO1);

        portfolioManager1 = EmployeeTestBuilder.anEmployee().withUserName(portfolioManagerDTO1.getUserName()).withRole(Role.Employee).withManager(aSFLead1).build();
        peopleManager1 = EmployeeTestBuilder.anEmployee().withUserName(peopleManagerDTO1.getUserName()).withRole(Role.Employee).withManager(portfolioManager1).build();
        aSFLead1 = EmployeeTestBuilder.anEmployee().withUserName(aSFLeadDTO1.getUserName()).withRole(Role.Employee).build();
        employee1 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO1.getUserName()).withRole(Role.Employee).withManager(peopleManager1).build();
        employee2 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO2.getUserName()).withRole(Role.Employee).withManager(peopleManager1).build();
        employee3 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO3.getUserName()).withRole(Role.Employee).withManager(peopleManager1).build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(peopleManager1);
        employeeRepository.save(portfolioManager1);
        employeeRepository.save(aSFLead1);
    }

    @Test
    public void setPortfolioManagerBasedOnAttributeFieldInLdap_shouldSetRoleToPortfolioManager() throws Exception {
        portfolioManagerRoleService.setPortfolioManagerBasedOnAttributeFieldInLdap(ldapEmployeeDTOList);

        assertThat(portfolioManager1.getRole()).isEqualTo(Role.PortfolioManager);
    }

    @Test
    public void setPortfolioManagerBasedOnManagerWhoHasManagers_shouldSetRoleToPortfolioManager() throws Exception {
        portfolioManagerRoleService.setPortfolioManagerBasedOnManagerWhoHasManagers();

        assertThat(portfolioManager1.getRole()).isEqualTo(Role.PortfolioManager);
    }
}