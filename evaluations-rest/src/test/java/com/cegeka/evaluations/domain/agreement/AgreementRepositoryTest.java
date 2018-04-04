package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.springConfiguration.SpringIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringIntegrationTest
public class AgreementRepositoryTest {

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee david, xan;

    private Agreement one, two, three;

    @Before
    public void setUp() throws Exception {
        david = EmployeeTestBuilder.david().build();
        xan = EmployeeTestBuilder.xan().build();
        employeeRepository.save(david);
        employeeRepository.save(xan);
        one = new Agreement(david, xan, 2017, 1, true, false);
        two = new Agreement(xan, david, 2017, 1, true, false);
        three = new Agreement(david, david, 2016, 2,  true, false);
        agreementRepository.save(one);
        agreementRepository.save(two);
        agreementRepository.save(three);
    }

    @Test
    public void getAllAgreements() throws Exception {
        Assertions.assertThat(agreementRepository.findAll()).contains(one, two);
    }

    @Test
    public void findByEmployeeAndYear() throws Exception {
        Assertions.assertThat(agreementRepository.findByEmployeeAndYear(david, 2016)).contains(three).doesNotContain(two, one);
    }

}