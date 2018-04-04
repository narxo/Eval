package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;

import java.time.LocalDate;

import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOPeriod.*;
import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOType.*;
import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOType.QSCORE;

public class StampCardInputDTO {

    public StampCardInputDTOType type;
    public StampCardInputDTOPeriod period;
    public String value;
    public String comment;
    public Boolean isActive;
    public Boolean isPastOrPresentStampCardPeriod;
    public String managerName;
    public String employeeName;
    public Boolean evalueeConfirmed;
    public Boolean evaluatorConfirmed;


    public StampCardInputDTO(QscoreDTO qscoreDTO) {
        type = QSCORE;
        period = getPeriodByQuadrimester(qscoreDTO.getQuadrimester());
        value = getQScoreValue(qscoreDTO.getScore());
        comment = qscoreDTO.getComment();
        isActive = determineActiveStateQuadrimester(qscoreDTO);
        managerName = qscoreDTO.getManager().getName();
        employeeName = qscoreDTO.getEmployee().getName();
        isPastOrPresentStampCardPeriod = checkIfQuadrimesterIsPastOrPresent(qscoreDTO);
    }

    public StampCardInputDTO(AgreementDTO agreementDTO) {
        type = AGREEMENT;
        period = getPeriodByMonth(agreementDTO.getDate().getMonth());
        value = getValueForAgreement(agreementDTO) + "";
        isActive = determineActiveStateMonth(agreementDTO);
        managerName = agreementDTO.getEvaluator();
        employeeName = agreementDTO.getEvaluee();
        evaluatorConfirmed = agreementDTO.isEvaluatorConfirmation();
        evalueeConfirmed = agreementDTO.isEvalueeConfirmation();
        isPastOrPresentStampCardPeriod = checkIfAgreementIsPastOrPresent(agreementDTO);
    }

    private int getValueForAgreement(AgreementDTO agreementDTO) {
        Boolean evaluatorConfirmedConversation = agreementDTO.isEvaluatorConfirmation();
        Boolean evalueeConfirmedConversation = agreementDTO.isEvalueeConfirmation();
        if (evalueeConfirmedConversation && evaluatorConfirmedConversation) {
            return 2;
        }
        if (evalueeConfirmedConversation || evaluatorConfirmedConversation) {
            return 1;
        }
        if (LocalDate.of(agreementDTO.getDate().getYear(), agreementDTO.getDate().getMonth(), 1)
                .isAfter(LocalDate.now().withDayOfMonth(1))) {
            return 3;

        }
        return 0;
    }

    private boolean determineActiveStateMonth(AgreementDTO agreementDTO) {
        if (period != null) {
            Integer month = period.getNumber();
            LocalDate periodStart = LocalDate.of(agreementDTO.getDate().getYear(), month, 1).minusDays(1);
            LocalDate periodEnd = periodStart.plusMonths(1).plusDays(15);
            LocalDate periodNow = LocalDate.now(TimeManager.getClock());

            if (periodNow.isAfter(periodStart) && periodNow.isBefore(periodEnd)) {
                return true;
            }
        }
        return false;
    }

    private boolean determineActiveStateQuadrimester(QscoreDTO qscoreDTO) {
        LocalDate periodStart;
        LocalDate periodEnd;

        if (qscoreDTO.getQuadrimester() == 1) {
            periodStart = LocalDate.of(qscoreDTO.getYear(), 4, 1);
            periodEnd = LocalDate.of(qscoreDTO.getYear(), 5, 16);
        }
        else {
            periodStart = LocalDate.of(qscoreDTO.getYear(), 12, 1);
            periodEnd = LocalDate.of(qscoreDTO.getYear() + 1, 1, 16);
        }
        if (LocalDate.now(TimeManager.getClock()).isAfter(periodStart) && LocalDate.now(TimeManager.getClock()).isBefore(periodEnd)) {
            return true;
        }
        return false;
    }

    private String getQScoreValue(int QScore) {
        if (QScore == 0) return "-";
        return QScore + "";
    }

    private boolean checkIfAgreementIsPastOrPresent(AgreementDTO agreementDTO) {
        if (period != null) {
            LocalDate now = LocalDate.now(TimeManager.getClock());
            LocalDate agreementDate = LocalDate.of(agreementDTO.getDate().getYear(), agreementDTO.getDate().getMonth(), 1).minusDays(1);

            return agreementDate.isBefore(now);
        }
        return false;
    }

    private boolean checkIfQuadrimesterIsPastOrPresent(QscoreDTO qscoreDTO) {
        LocalDate quadrimesterStart;
        LocalDate now = LocalDate.now(TimeManager.getClock());

        if (qscoreDTO.getQuadrimester() == 1) {
            quadrimesterStart = LocalDate.of(qscoreDTO.getYear(), 4, 1);
        } else if (qscoreDTO.getQuadrimester() == 2) {
            quadrimesterStart = LocalDate.of(qscoreDTO.getYear(), 8, 1);
        } else {
            quadrimesterStart = LocalDate.of(qscoreDTO.getYear(), 12, 1);
        }
        if (quadrimesterStart.minusDays(1).isBefore(now)) {
            return true;
        }
        return false;
    }
}
