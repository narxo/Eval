package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.employee.EmployeeService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class EmployeeControllerTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void whenGetEmployeeDtoOfTeamMembers_shouldActivateCorrespondingMethodeInEmployeeService() throws Exception {
        employeeController.getEmployeeDtoOfTeamMembers("Kiki");
        verify(employeeService).getEmployeeDtoOfTeamMembers("Kiki");
    }

    @Test
    public void whenGetEmployeeDtoByUserName_shouldActivateCorrespondingMethodeInEmployeeService() throws Exception {
        employeeController.getEmployeeDtoByUserName("Kiki");
        verify(employeeService).getEmployeeDtoByUsername("Kiki");
    }

    @Test
    public void whenGetAllEmployeesForAsfLead_shouldActivateCorrespondingMethodeInEmployeeService() throws Exception {
        employeeController.getAllEmployeesForAsfLead();
        verify(employeeService).getAllEmployeesForAsfLead();
    }
}