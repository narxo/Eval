package com.cegeka.evaluations.domain.employee;

import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.springConfiguration.SpringIntegrationTest;
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
public class EmployeeRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee david, xan, employee, portfolioManager1, portfolioManager2, peopleManager1, peopleManager2, peopleManager3;

    @Before
    public void setUp() throws Exception {
        david = EmployeeTestBuilder.david().build();
        xan = EmployeeTestBuilder.xan().build();
        employee = EmployeeTestBuilder.anEmployee().build();
        portfolioManager1 = EmployeeTestBuilder.anEmployee().withRole(Role.PortfolioManager).build();
        portfolioManager2 = EmployeeTestBuilder.anEmployee().withRole(Role.PortfolioManager).build();
        employeeRepository.save(portfolioManager1);
        employeeRepository.save(portfolioManager2);
        employeeRepository.save(david);
        employeeRepository.save(xan);
        employeeRepository.save(employee);
    }

    @Test
    public void getAllEmployees() {
        assertThat(employeeRepository.findAll()).contains(david, xan);
    }

    @Test
    public void findByUserName() throws Exception {
        assertThat(employeeRepository.findByUserName("davids")).isEqualTo(david);
    }

    @Test
    public void findByUserName_doesNotExist_ReturnsNull() {
        assertThat(employeeRepository.findByUserName("qwerty")).isNull();
    }

    @Test
    public void findByManager() throws Exception {
        david.setManager(xan);
        employee.setManager(xan);
        assertThat(employeeRepository.findByManager(xan)).contains(david, employee);
    }

    @Test
    public void findAllPortfolioManagersTest() throws Exception {
        assertThat(employeeRepository.findAllPortfolioManagers()).containsExactly(portfolioManager1,portfolioManager2);
    }
}