package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.domain.stampcard.StampCardInputDTOPeriod;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.cegeka.evaluations.domain.employee.Role.ASFLead;
import static com.cegeka.evaluations.domain.employee.Role.PeopleManager;
import static com.cegeka.evaluations.domain.employee.Role.PortfolioManager;
import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOPeriod.*;

@Service
public class QscoreService {

    @Autowired
    private QscoreRepository qscoreRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private QscoreMapper qscoreMapper;

    @Transactional
    public List<QscoreDTO> findQScoresByUserName(String userName) {
        Employee employee = employeeRepository.findByUserName(userName.toLowerCase());
        List<Qscore> qscoreList = findQScoresByEmployee(employee);
        List<QscoreDTO> qscoreDTOList = qscoreMapper.mapQscoreListToDTOList(qscoreList);
        return qscoreDTOList;
    }

    private List<Qscore> findQScoresByEmployee(Employee employee) {
        List<Qscore> qscoreList = qscoreRepository.findByEmployeeAndYear(employee, LocalDate.now(TimeManager.getClock()).getYear());
        if (qscoreList.isEmpty()) {
            qscoreList = createNewQscoreListForEmployee(employee);
        }
        return qscoreList;
    }

    public QscoreDTO findLastQscoreDTOByUserName(String userName){
        List<QscoreDTO> allScoresForEmployee = findQScoresByUserName(userName);
        QscoreDTO mostRecent = allScoresForEmployee.get(0);
        for (QscoreDTO dto : allScoresForEmployee){
            if(dto.getYear()>mostRecent.getYear()){
                mostRecent = dto;
            }
        }
        return mostRecent;
    }

    private List<Qscore> createNewQscoreListForEmployee(Employee employee) {
        List<Qscore> qscoreList = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            Qscore qscore = new Qscore(employee, employee.getManager(), LocalDate.now(TimeManager.getClock()).getYear(), i, 0);
            qscoreList.add(qscore);
        }
        qscoreRepository.save(qscoreList);
        return qscoreList;
    }

    @Transactional
    public void updateQscore(UpdateQscoreDTO updateQscoreDTO) {
        Employee currentEvaluator = employeeRepository.findByUserName(updateQscoreDTO.evaluator);
        Employee currentEvaluee = employeeRepository.findByUserName(updateQscoreDTO.evaluee);
        if (withinDeadline(updateQscoreDTO.quadrimester) && (isEvaluator(currentEvaluator,currentEvaluee))
                && (updateQscoreDTO.qScore > 0)) {
            Qscore qscore = getQscoreBasedOnQuadrimesterPeriod(updateQscoreDTO.quadrimester, currentEvaluee);
            qscore.setScore(updateQscoreDTO.qScore);
            qscore.setComment(updateQscoreDTO.comment);
        }
    }

    private boolean isEvaluator(Employee currentEvaluator, Employee currentEvaluee) {
        return (currentEvaluator == currentEvaluee.getManager());
    }

    private Qscore getQscoreBasedOnQuadrimesterPeriod(String quadrimester, Employee Employee) {
        List<Qscore> evalueeQscoreList = qscoreRepository.findByEmployeeAndYear(Employee, LocalDate.now(TimeManager.getClock()).getYear());

        Integer quadrimesterNumber = getQuadrimesterNumberByPeriod(quadrimester);

        return evalueeQscoreList.stream()
                .filter(q -> q.getQuadrimester().equals(quadrimesterNumber))
                .findFirst()
                .orElse(null);
    }

    private boolean withinDeadline(String quadrimester) {
        Integer quadrimesterNumber = getQuadrimesterNumberByPeriod(quadrimester);

        LocalDate periodStart = getPeriodStartDateForDeadline(quadrimesterNumber);
        LocalDate periodEnd = periodStart.plusMonths(4).plusDays(15);
        LocalDate periodNow = LocalDate.now(TimeManager.getClock());

        if (periodNow.isAfter(periodStart) && periodNow.isBefore(periodEnd)) {
            return true;
        }
        return false;
    }

    private LocalDate getPeriodStartDateForDeadline(int quadrimesterNumber) {
        if (quadrimesterNumber == 1) {
            return LocalDate.of(TimeManager.getNow().getYear(), 1, 1);
        } else if (quadrimesterNumber == 2) {
            return LocalDate.of(TimeManager.getNow().getYear(), 5, 1);
        }
        return LocalDate.of(TimeManager.getNow().getYear(), 9, 1);
    }

    public List<QscoreDTO> findQScoresByUserNameAndYear(String userName, int year) {
        Employee employee = employeeRepository.findByUserName(userName.toLowerCase());
        List<Qscore> qscoreList = findQScoresByEmployeeAndYear(employee, year);
        List<QscoreDTO> qscoreDTOList = qscoreMapper.mapQscoreListToDTOList(qscoreList);
        return qscoreDTOList;
    }

    private List<Qscore> findQScoresByEmployeeAndYear(Employee employee, int year) {
        List<Qscore> qscoreList = qscoreRepository.findByEmployeeAndYear(employee, year);
        if (qscoreList.isEmpty()) {
            qscoreList = createNewQscoreListForEmployee(employee);
        }
        return qscoreList;
    }
}
