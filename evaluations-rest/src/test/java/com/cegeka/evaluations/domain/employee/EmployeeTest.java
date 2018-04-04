package com.cegeka.evaluations.domain.employee;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EmployeeTest {

    @Test
    public void toString_shouldReturnString() throws Exception {
        Employee employee = EmployeeTestBuilder.anEmployee().build();
        Assertions.assertThat(employee.toString()).isEqualTo("Employee{id=null, firstName='firstname', lastName='lastname', userName='username', role='Employee', manager=null}");
    }

}