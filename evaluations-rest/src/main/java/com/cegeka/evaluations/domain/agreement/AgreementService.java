package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.stampcard.StampCardInputDTOPeriod;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementService {

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AgreementMapper agreementMapper;

    @Transactional
    public List<AgreementDTO> findAgreementsByUsername(String userName) {
        Employee employee = employeeRepository.findByUserName(userName.toLowerCase());
        List<Agreement> agreements = findAgreementsByEmployee(employee);
        List<AgreementDTO> dtoList = agreementMapper.mapAgreementListtoDTOList(agreements);
        return dtoList;
    }

    @Transactional
    public List<Agreement> findAgreementsByEmployee(Employee employee) {
        List<Agreement> agreements = agreementRepository.findByEmployeeAndYear(employee, LocalDate.now(TimeManager.getClock()).getYear());
        if (agreements.isEmpty()) {
            agreements = createNewAgreementsForEmployee(employee, TimeManager.getCurrentYear());
        }
        setCurrentManagerOnFutureAgreements(agreements);

        return agreements;
    }

    List<Agreement> createNewAgreementsForEmployee(Employee employee, int year) {
        List<Agreement> agreements = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            Agreement agreement = new Agreement(employee, employee.getManager(), year, i, false, false);
            agreements.add(agreement);
            agreementRepository.save(agreement);
        }
        return agreements;
    }

    void setCurrentManagerOnFutureAgreements(List<Agreement> agreements) {
        agreements.stream()
                .filter(agreement -> agreement.getYear().equals(LocalDate.now(TimeManager.getClock()).getYear()))
                .filter(agreement -> agreement.getMonth() > LocalDate.now(TimeManager.getClock()).getMonthValue())
                .forEach(agreement -> getCurrentManagerAndSetItOnAgreement(agreement));
    }

    private void getCurrentManagerAndSetItOnAgreement(Agreement agreement) {
        Employee currentManager = agreement.getEmployee().getManager();
        agreement.setManager(currentManager);
    }

    @Transactional
    public void updateAgreement(AgreementDTO agreementDTO) {
        Agreement agreement = agreementRepository.findOne(agreementDTO.getId());
        setConfirmationsOfManagerAndTeamMember(agreementDTO, agreement);
        agreementRepository.save(agreement);
    }

    private void setConfirmationsOfManagerAndTeamMember(AgreementDTO agreementDTO, Agreement agreement) {
        agreement.setEvaluatorConfirmation(agreementDTO.isEvaluatorConfirmation());
        agreement.setEvalueeConfirmation(agreementDTO.isEvalueeConfirmation());
    }

    @Transactional
    public void reverseConfirmation(ReverseConfirmationDTO reverseConfirmationDTO) {
        if (withinDeadline(reverseConfirmationDTO.period)) {

            Employee currentEvaluator = employeeRepository.findByUserName(reverseConfirmationDTO.evaluator);
            Employee currentEvaluee = employeeRepository.findByUserName(reverseConfirmationDTO.evaluee);

            if (currentEvaluator.getId().equals(currentEvaluee.getId())) {
                reverseEvalueeConfirmation(reverseConfirmationDTO.period, currentEvaluator);
            } else if (isEvaluator(currentEvaluator, currentEvaluee)) {
                reverseEvaluatorConfirmation(reverseConfirmationDTO.period, currentEvaluee);
            }
        }
    }

    private boolean withinDeadline(String period) {
        Integer month = StampCardInputDTOPeriod.getMonthNumberByPeriod(period);

        LocalDate periodStart = LocalDate.of(TimeManager.getCurrentYear(), month, 1);
        LocalDate periodEnd = periodStart.plusMonths(1).plusDays(15);
        LocalDate periodNow = LocalDate.now(TimeManager.getClock());

        if (periodNow.isAfter(periodStart) && periodNow.isBefore(periodEnd)) {
            return true;
        }
        return false;
    }

    private boolean isEvaluator(Employee currentEvaluator, Employee currentEvaluee) {
        return (currentEvaluator == currentEvaluee.getManager());
    }

    private void reverseEvalueeConfirmation(String period, Employee currentEvaluator) {
        Agreement agreement = getAgreementBasedOnMonthPeriod(period, currentEvaluator);
        agreement.setEvalueeConfirmation(!agreement.getEvalueeConfirmation());
    }

    private void reverseEvaluatorConfirmation(String period, Employee currentEvaluee) {
        Agreement agreement = getAgreementBasedOnMonthPeriod(period, currentEvaluee);
        agreement.setEvaluatorConfirmation(!agreement.getEvaluatorConfirmation());
    }

    private Agreement getAgreementBasedOnMonthPeriod(String period, Employee Employee) {
        List<Agreement> evalueeAgreementsList = agreementRepository.findByEmployeeAndYear(Employee, LocalDate.now(TimeManager.getClock()).getYear());

        Integer month = StampCardInputDTOPeriod.getMonthNumberByPeriod(period);

        return evalueeAgreementsList.stream()
                .filter(n -> n.getMonth().equals(month))
                .findFirst()
                .orElse(null);
    }

    public List<AgreementDTO> findAgreementsByUsernameAndYear(String userName, int year) {
        Employee employee = employeeRepository.findByUserName(userName.toLowerCase());
        List<Agreement> agreements = findAgreementsByEmployeeAndYear(employee, year);
        List<AgreementDTO> dtoList = agreementMapper.mapAgreementListtoDTOList(agreements);
        return dtoList;
    }
    @Transactional
    public List<Agreement> findAgreementsByEmployeeAndYear(Employee employee, int year) {
        List<Agreement> agreements = agreementRepository.findByEmployeeAndYear(employee, year);
        if (agreements.isEmpty()) {
            agreements = createNewAgreementsForEmployee(employee, year);
        }
        setCurrentManagerOnFutureAgreements(agreements);

        return agreements;
    }
}



