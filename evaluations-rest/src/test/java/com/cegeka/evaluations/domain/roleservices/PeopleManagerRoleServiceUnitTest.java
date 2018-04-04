package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.infrastructure.UnitTest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PeopleManagerRoleServiceUnitTest extends UnitTest {

    @InjectMocks
    private PeopleManagerRoleService peopleManagerRoleService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee1, employee2, employee3, employee4, employee5;

    private List<Employee> validPeopleManagers;

    private List<Employee> peopleManagersInDatabase;

    @Before
    public void setUp() throws Exception {
        employee1 = EmployeeTestBuilder.anEmployee().withUserName("employee1").withRole(Role.Employee).withManager(employee4).build();
        employee2 = EmployeeTestBuilder.anEmployee().withUserName("employee2").withRole(Role.Employee).withManager(employee4).build();
        employee3 = EmployeeTestBuilder.anEmployee().withUserName("employee3").withRole(Role.Employee).withManager(employee4).build();
        employee4 = EmployeeTestBuilder.anEmployee().withUserName("employee4").withRole(Role.PeopleManager).build();
        employee5 = EmployeeTestBuilder.anEmployee().withUserName("employee5").withRole(Role.PeopleManager).build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);

        validPeopleManagers = new ArrayList<>();
        validPeopleManagers.add(employee4);

        peopleManagersInDatabase = new ArrayList<>();
        peopleManagersInDatabase.add(employee4);
        peopleManagersInDatabase.add(employee5);
    }

    @Test
    public void revokePeopleManagerRole_ShouldChangeRoleToEmployeeIfSubjectHasNoEmployeesUnderThem() throws Exception {
        when(employeeRepository.findByManagerOfAnotherEmployee()).thenReturn(validPeopleManagers);
        when(employeeRepository.findByRolePeopleManager()).thenReturn(peopleManagersInDatabase);

        peopleManagerRoleService.revokePeopleManagerRole();

        Assertions.assertThat(employee5.getRole()).isEqualTo(Role.Employee);
    }

    @Test
    public void revokePeopleManagerRole_ShouldNotChangeRoleWhenSubjectHasEmployeesUnderThem() throws Exception {
        when(employeeRepository.findByManagerOfAnotherEmployee()).thenReturn(validPeopleManagers);
        when(employeeRepository.findByRolePeopleManager()).thenReturn(peopleManagersInDatabase);

        peopleManagerRoleService.revokePeopleManagerRole();

        Assertions.assertThat(employee4.getRole()).isEqualTo(Role.PeopleManager);
    }

}