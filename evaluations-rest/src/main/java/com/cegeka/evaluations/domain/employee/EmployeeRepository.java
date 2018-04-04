package com.cegeka.evaluations.domain.employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByUserName(String username);

    List<Employee> findByManager(Employee manager);

    List<Employee> findAll();

    @Query(value = "Select manager from Employee manager where manager.role= com.cegeka.evaluations.domain.employee.Role.PeopleManager")
    List<Employee> findByRolePeopleManager();

    @Query(value = "Select distinct manager from Employee emp join emp.manager as manager where manager.role= com.cegeka.evaluations.domain.employee.Role.PeopleManager")
    List<Employee> findByManagerOfAnotherEmployee();

    @Query(value = "Select manager from Employee manager where manager.role= com.cegeka.evaluations.domain.employee.Role.PortfolioManager")
    List<Employee> findAllPortfolioManagers();
}
