package com.cegeka.evaluations.domain.employee;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class EmployeeDtoMapperTest {

    private EmployeeDtoMapper employeeDtoMapper;

    private Employee employee, manager, asf;
    private EmployeeDTO employeeDTO, asfDTO;

    @Before
    public void setUp() throws Exception {
        manager = EmployeeTestBuilder.anEmployee().withRole(Role.PortfolioManager).withUserName("TestManager").build();
        employee = EmployeeTestBuilder.david().withRole(Role.Employee).withManager(manager).build();
        asf = EmployeeTestBuilder.xan().withRole(Role.ASFLead).build();
        employeeDTO = new EmployeeDTO("David", "Scheers", "davids", Role.Employee, "TestManager");
        employeeDtoMapper = new EmployeeDtoMapper();
        asfDTO = new EmployeeDTO("Xan", "Vranckaert", "xanV", Role.ASFLead);
    }

    @Test
    public void mapEmployeeToLoggedInEmployeeDTO_ShouldReturnLoggedInEmployeeDTOWithSameValues() {
        assertThat(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(employee)).isEqualToComparingFieldByField(employeeDTO);
    }

    @Test
    public void mapAsfLeadToLoggedInEmployeeDTO_shouldReturnLoggedInEmployeeDTOWithSameValues() throws Exception {
        assertThat(employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(asf)).isEqualToComparingFieldByField(asfDTO);
    }
}