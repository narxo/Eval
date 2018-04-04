package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementDate;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.cegeka.evaluations.domain.builders.AgreementDTOsWithListForTest.*;
import static com.cegeka.evaluations.domain.builders.QscoreDTOsWithListForTest.*;
import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOPeriod.*;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

public class StampCardInputDTOTest {
    private Employee employee, manager;
    private QscoreDTO testInvalidQuarterQscore;
    private AgreementDTO testInvalidAgreementDto,
            testAgreementConfirmedEvalue, testAgreementConfirmedEvaluator, testAgreementConfirmedByboth, testAgreementNotConfirmed, testFutureAgreementNotConfirmed;
    private long long3 = 3;
    private StampCardInputDTO stampCardInputDTO;

    @Before
    public void setUp() throws Exception {
        employee = EmployeeTestBuilder.anEmployee().build();
        manager = EmployeeTestBuilder.anEmployee().build();
        testInvalidQuarterQscore = new QscoreDTO(1, employee, manager, 2016, 5, 1);
        testInvalidAgreementDto = new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2016, 13));
        testAgreementConfirmedEvaluator = new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2016, 12));
        testAgreementConfirmedEvalue = new AgreementDTO(1, "evaluator", "evaluee", false, true, new AgreementDate(2016, 12));
        testAgreementConfirmedByboth = new AgreementDTO(1, "evaluator", "evaluee", true, true, new AgreementDate(2016, 12));
        testAgreementNotConfirmed = new AgreementDTO(1, "evaluator", "evaluee", false, false, new AgreementDate(2016, 12));
        testFutureAgreementNotConfirmed = new AgreementDTO(1, "evaluator", "evaluee", false, false, new AgreementDate(LocalDate.now().getYear()+1, 12));

    }

    @Test
    public void getQscorePeriod_shouldReturnCorrectQscoreValue() {
        testPeriodFromStampcardInputDtoForQscore(testQscoreDTOQ1, Q1);
        testPeriodFromStampcardInputDtoForQscore(testQscoreDTOQ2, Q2);
        testPeriodFromStampcardInputDtoForQscore(testQscoreDTOQ3, Q3);
        testPeriodFromStampcardInputDtoForQscore(testQscoreDTOQ4, Q4);
    }

    @Test
    public void givenInvalidQuarterInput_getQscorePeriod_shouldReturnNull() {
        StampCardInputDTO stampCardInputDTOQ1 = new StampCardInputDTO(testInvalidQuarterQscore);
        assertThat(stampCardInputDTOQ1.period).isNull();
    }

    @Test
    public void givenInvalidMonthInput_getAgreementPeriod_shouldReturnNull() {
        StampCardInputDTO stampCardInputInvalid = new StampCardInputDTO(testInvalidAgreementDto);
        assertThat(stampCardInputInvalid.period).isNull();
    }

    @Test
    public void getAgreementPeriod_shouldReturnCorrectMonth() {
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto1JAN, JAN);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto2FEB, FEB);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto3MAR, MAR);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto4APR, APR);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto5MAY, MAY);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto6JUN, JUN);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto7JUL, JUL);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto8AUG, AUG);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto9SEP, SEP);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto10OCT, OCT);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto11NOV, NOV);
        testPeriodFromStamcardInputDtoForAgreements(testAgreementDto12DEC, DEC);

    }

    @Test
    public void getValueForAgreement_shouldReturnCorrectValue() {
        getValueForAgreementTest(testAgreementConfirmedEvaluator, "1");
        getValueForAgreementTest(testAgreementConfirmedEvalue, "1");
        getValueForAgreementTest(testAgreementConfirmedByboth, "2");
        getValueForAgreementTest(testAgreementNotConfirmed, "0");
        getValueForAgreementTest(testFutureAgreementNotConfirmed, "3");
    }

    @Test
    public void getValueForAgreement_ifAgreementIsInFutureMonth_shouldReturn3() {

    }

    @Test
    public void givenAgreementType_andCurrentDateBefore16thOfAMonth_StampcardInputDTOIsActive_shouldReturnTrueForThisMonthAndThePreviousMonth() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 3, 4, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
        StampCardInputDTO sCIDTOFEB = new StampCardInputDTO(new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2017, 2)));
        assertThat(sCIDTOFEB.isActive).isTrue();
        StampCardInputDTO sCIDTOMAR = new StampCardInputDTO(new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2017, 3)));
        assertThat(sCIDTOMAR.isActive).isTrue();
        StampCardInputDTO sCIDTOJAN = new StampCardInputDTO(new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2017, 1)));
        assertThat(sCIDTOJAN.isActive).isFalse();
        StampCardInputDTO sCIDTOAPR = new StampCardInputDTO(new AgreementDTO(1, "evaluator", "evaluee", true, false, new AgreementDate(2017, 4)));
        assertThat(sCIDTOAPR.isActive).isFalse();
    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_firstQuadrimesterSecondActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 5,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isTrue();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isFalse();

    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_firstQuadrimesterFirstActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 4,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isTrue();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isFalse();

    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_secondQuadrimesterFirstActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 8,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isTrue();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isFalse();

    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_secondQuadrimesterSecondActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 9,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isTrue();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isFalse();

    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_thirdQuadrimesterFirstActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 12,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isTrue();

    }

    @Test
    public void givenQscoreType_andCurrentDateBefore16thOfMonthAfterQuadrimester_thirdQuadrimesterSecondActiveMonthStampcardInputDTOIsActive_shouldReturnTrueForThisQuadrimesterOnly() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2018, 1,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO stampcardInputDTOMAY = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 1, 3));
        assertThat(stampcardInputDTOMAY.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOSEP = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 2, 3));
        assertThat(stampcardInputDTOSEP.isActive).isFalse();

        StampCardInputDTO stampcardInputDTOJAN = new StampCardInputDTO(new QscoreDTO(1,employee, manager, 2017, 3, 3));
        assertThat(stampcardInputDTOJAN.isActive).isTrue();

    }

    private void getValueForAgreementTest(AgreementDTO testAgreement, String howManyPeopleConfirmedAgreement) {
        StampCardInputDTO stampCardInputDTOQ1 = new StampCardInputDTO(testAgreement);
        assertThat(stampCardInputDTOQ1.value).isEqualTo(howManyPeopleConfirmedAgreement);
    }

    private void testPeriodFromStampcardInputDtoForQscore(QscoreDTO testQscore, StampCardInputDTOPeriod expectedQuarter) {
        StampCardInputDTO stampCardInputDTOQscore = new StampCardInputDTO(testQscore);
        assertThat(stampCardInputDTOQscore.period).isEqualTo(expectedQuarter);
    }

    private void testPeriodFromStamcardInputDtoForAgreements(AgreementDTO testAgreement, StampCardInputDTOPeriod expectedMonth) {
        StampCardInputDTO stampCardInputDTOAgreement = new StampCardInputDTO(testAgreement);
        assertThat(stampCardInputDTOAgreement.period).isEqualTo(expectedMonth);
    }

    @Test
    public void givenAgreementDtoAfterCurrentMonth_ShouldReturnFalse() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2016, 1,4,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO actualStampCardInputDTO = new StampCardInputDTO(testAgreementDto2FEB);

        assertThat(actualStampCardInputDTO.isPastOrPresentStampCardPeriod).isFalse();
    }

    @Test
    public void givenAgreementDtoOfCurrentMonth_ShouldReturnTrue() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2016, 2,1,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO actualStampCardInputDTO = new StampCardInputDTO(testAgreementDto2FEB); //feb 2016

        assertThat(actualStampCardInputDTO.isPastOrPresentStampCardPeriod).isTrue();
    }

    @Test
    public void givenQscoreDtoOfCurrentQuadrimester_ShouldReturnTrue() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 8,2,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO actualStampCardInputDTO = new StampCardInputDTO(testQscoreDTOQ2); //Q2 2017 (5/2017 - 8/2017)

        assertThat(actualStampCardInputDTO.isPastOrPresentStampCardPeriod).isTrue();
    }

    @Test
    public void givenQscoreDtoOfFutureQuadrimester_ShouldReturnFalse() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 4,30,10,10).toInstant(UTC), ZoneId.systemDefault()));

        StampCardInputDTO actualStampCardInputDTO = new StampCardInputDTO(testQscoreDTOQ2); //Q2 2017 (5/2017 - 8/2017)

        assertThat(actualStampCardInputDTO.isPastOrPresentStampCardPeriod).isFalse();
    }
}