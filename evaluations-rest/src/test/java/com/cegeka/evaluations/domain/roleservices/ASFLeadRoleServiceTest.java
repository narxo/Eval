package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.infrastructure.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static com.cegeka.evaluations.domain.employee.Role.ASFLead;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class ASFLeadRoleServiceTest extends UnitTest{

    @InjectMocks
    private ASFLeadRoleService asfLeadRoleService;

    @Mock
    private EmployeeRepository employeeRepository;

    private List<String> asfLeads = new ArrayList<>();

    @Before
    public void setUp() {
        asfLeads.add("katrienvdv");
        asfLeads.add("seppeg");
        ReflectionTestUtils.setField(asfLeadRoleService, "asfLeads", asfLeads);
    }

    @Test
    public void setRoleForASFLeads_ShouldSetRolesToASFLead() {
        Employee asfLead1 = EmployeeTestBuilder.anEmployee().withUserName("katrienvdv").build();
        Employee asfLead2 = EmployeeTestBuilder.anEmployee().withUserName("seppeg").build();

        when(employeeRepository.findByUserName("katrienvdv")).thenReturn(asfLead1);
        when(employeeRepository.findByUserName("seppeg")).thenReturn(asfLead2);

        asfLeadRoleService.setRoleForASFLeads();

        assertThat(asfLead1.getRole()).isEqualTo(ASFLead);
        assertThat(asfLead2.getRole()).isEqualTo(ASFLead);
    }
}