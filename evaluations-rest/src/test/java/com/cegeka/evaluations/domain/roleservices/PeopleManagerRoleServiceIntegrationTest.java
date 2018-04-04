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
public class PeopleManagerRoleServiceIntegrationTest {

    @Autowired
    private PeopleManagerRoleService peopleManagerRoleService;

    @Autowired
    private EmployeeRepository employeeRepository;

    private LdapEmployeeDTO employeeDTO1, employeeDTO2, employeeDTO3, employeeDTO4;

    private Employee employee1, employee2, employee3, employee4;

    private List<LdapEmployeeDTO> ldapEmployeeDTOList;

    @Before
    public void setUp() throws Exception {
        employeeDTO1 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Domien").withCN("Domien").withManagerCN("Seppe").build();
        employeeDTO2 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Gerri").withCN("Gerri").withManagerCN("Seppe").build();
        employeeDTO3 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Jeroen").withCN("Jeroen").withManagerCN("Seppe").build();
        employeeDTO4 = LDAPEmployeeDTOTestBuilder.anEmployee().withUserName("Seppe").withCN("Seppe").build();
        ldapEmployeeDTOList = Arrays.asList(employeeDTO1, employeeDTO2, employeeDTO3, employeeDTO4);

        employee1 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO1.getUserName()).withRole(Role.Employee).withManager(employee4).build();
        employee2 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO2.getUserName()).withRole(Role.Employee).withManager(employee4).build();
        employee3 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO3.getUserName()).withRole(Role.Employee).withManager(employee4).build();
        employee4 = EmployeeTestBuilder.anEmployee().withUserName(employeeDTO4.getUserName()).withRole(Role.Employee).withManager(employee4).build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
    }

    @Test
    public void setManagerWithPeopleManagerRole_shouldSetRoleOfManagerToManager() throws Exception {
        peopleManagerRoleService.setManagerWithPeopleManagerRole(ldapEmployeeDTOList);

        assertThat(employee4.getRole()).isEqualTo(Role.PeopleManager);
    }

}