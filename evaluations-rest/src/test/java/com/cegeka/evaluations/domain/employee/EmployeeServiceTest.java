package com.cegeka.evaluations.domain.employee;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static com.cegeka.evaluations.domain.employee.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDtoMapper employeeDtoMapper;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeDtoOfTeamMembers_shouldReturnListOfEmployeeDto() throws Exception {
        Employee len = EmployeeTestBuilder.anEmployee().withUserName("leka").build();
        Employee gerri = EmployeeTestBuilder.anEmployee().withUserName("gvdhaege").build();
        Employee bicky = EmployeeTestBuilder.anEmployee().withUserName("bicky").build();
        List<Employee> employeeList = Arrays.asList(len, gerri, bicky);
        EmployeeDTO lenDTO = new EmployeeDTO("firstname", "lastname", "leka", Employee,"TestManager");
        EmployeeDTO gerriDTO = new EmployeeDTO("firstname", "lastname", "gvdhaege", Employee,"TestManager");
        EmployeeDTO bickyDTO = new EmployeeDTO("firstname", "lastname", "bicky", Employee,"TestManager");
        List<EmployeeDTO> expected = Arrays.asList(lenDTO, gerriDTO, bickyDTO);

        Employee testPeopleManager = new Employee("Jeroen", "Stroobants", "stroobaj", PeopleManager);

        when(employeeRepository.findByUserName("stroobaj")).thenReturn(testPeopleManager);
        when(employeeRepository.findByManager(testPeopleManager)).thenReturn(Arrays.asList(len,gerri,bicky));
        when(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(len)).thenReturn(lenDTO);
        when(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(gerri)).thenReturn(gerriDTO);
        when(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(bicky)).thenReturn(bickyDTO);

        List<EmployeeDTO> actual = employeeService.getEmployeeDtoOfTeamMembers("stroobaj");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getLoggedInEmployeeByUsername_shouldReturnLoggedInEmployeeDto() throws Exception {
        Employee manager = EmployeeTestBuilder.anEmployee().withRole(Role.PortfolioManager).withUserName("TestManager").build();
        Employee len = EmployeeTestBuilder.anEmployee().withUserName("leka").withRole(Employee).withManager(manager).build();
        when(employeeRepository.findByUserName("leka")).thenReturn(len);
        EmployeeDTO expected = new EmployeeDTO("firstname", "lastname","leka", Employee,"TestManager");
        when(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(len)).thenReturn(expected);
        EmployeeDTO actual = employeeService.getEmployeeDtoByUsername("leka");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAllEmployeesForAsfLead_shouldReturnCorrectList() throws Exception {

        Employee len = EmployeeTestBuilder.anEmployee().withUserName("leka").withRole(Role.PortfolioManager).build();
        Employee gerri = EmployeeTestBuilder.anEmployee().withUserName("gvdhaege").withRole(Role.PortfolioManager).build();
        Employee bicky = EmployeeTestBuilder.anEmployee().withUserName("bicky").withRole(Role.PortfolioManager).build();
        Employee kiki = EmployeeTestBuilder.anEmployee().withUserName("kiki").withRole(Role.PortfolioManager).build();

        Employee thibault = EmployeeTestBuilder.anEmployee().withUserName("thibaultk").withRole(Role.PeopleManager).build();
        Employee ralph = EmployeeTestBuilder.anEmployee().withUserName("ralphv").withRole(Role.PeopleManager).build();
        Employee domien = EmployeeTestBuilder.anEmployee().withUserName("domienl").withRole(Role.PeopleManager).build();

        EmployeeDTO lenDTO = new EmployeeDTO("firstname", "lastname", "leka", PortfolioManager,"TestManager");
        EmployeeDTO gerriDTO = new EmployeeDTO("firstname", "lastname", "gvdhaege", PortfolioManager,"TestManager");
        EmployeeDTO bickyDTO = new EmployeeDTO("firstname", "lastname", "bicky", PortfolioManager,"TestManager");
        EmployeeDTO kikiDTO = new EmployeeDTO("firstname", "lastname", "kiki", PortfolioManager,"TestManager");

        EmployeeDTO thibaultDTO = new EmployeeDTO("firstname", "lastname", "thibaultk", PeopleManager,null);
        EmployeeDTO ralphDTO = new EmployeeDTO("firstname", "lastname", "ralphv", PeopleManager,null);
        EmployeeDTO domienDTO = new EmployeeDTO("firstname", "lastname", "domienl", PeopleManager,null);


    }
}