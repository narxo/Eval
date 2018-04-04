package com.cegeka.evaluations.domain.qscores;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringIntegrationTest
public class QscoreRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QscoreRepository qscoreRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee manager, employee;
    private Qscore q1,q2,q3,q4;

    @Before
    public void setUp() throws Exception {
        employee = EmployeeTestBuilder.anEmployee().build();
        manager = EmployeeTestBuilder.anEmployee().build();
        employeeRepository.save(employee);
        employeeRepository.save(manager);
        q1 = new Qscore(employee,manager,2016,1,5);
        q2 = new Qscore(employee,manager,2016,2,5);
        q3 = new Qscore(employee,manager,2016,3,5);
        q4 = new Qscore(employee,manager,2016,4,5);
        qscoreRepository.save(q1);
        qscoreRepository.save(q2);
        qscoreRepository.save(q3);
        qscoreRepository.save(q4);
    }

    @Test
    public void getAllqscores() throws Exception {
        Assertions.assertThat(qscoreRepository.findAll()).contains(q1,q2,q3,q4);
    }
    @Test
    public void findByEmployeeAndYear_returns() {
        assertThat(qscoreRepository.findByEmployeeAndYear(employee,2016)).contains(q1,q2,q3,q4);
    }
}