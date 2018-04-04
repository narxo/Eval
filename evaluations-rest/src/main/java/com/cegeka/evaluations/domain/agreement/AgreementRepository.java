package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer> {
    List<Agreement> findByEmployeeAndYear(Employee employee, int year);
}
