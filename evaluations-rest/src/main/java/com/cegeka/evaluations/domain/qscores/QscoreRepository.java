package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QscoreRepository extends CrudRepository<Qscore, Integer>{

    List<Qscore> findByEmployeeAndYear(Employee employee, int year);

}
