package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.when;

public class AgreementServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AgreementRepository agreementRepository;

    @InjectMocks
    private AgreementService agreementService;

    @Mock
    private AgreementMapper agreementMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee, originalManager, newManager, peopleManager, portfolioManager, aSFLead;

    private Agreement agreementWithManager1, agreementWithManager2, agreementWithManager3, agreementWithManager4, agreementWithManager5, agreementWithManager6, agreementWithManager7, agreementWithManager8, agreementWithManager9, agreementWithManager10, agreementWithManager11, agreementWithManager12;
    private Agreement agreement1, agreement2, agreement3, agreement4, agreement5, agreement6, agreement7, agreement8, agreement9, agreement10, agreement11, agreement12;
    private List<Agreement> testAgreementListLong, testAgreementListLongWithManager;
    private List<Agreement> emptyTestAgreementList;

    private List<AgreementDTO> testAgreementDTOListShort;
    private List<AgreementDTO> testAgreementDTOListEmpty;
    private AgreementDTO agreementDTO1, agreementDTO2;
    private ReverseConfirmationDTO reverseConfirmationDTO;


    @Before
    public void setUp() throws Exception {
        aSFLead = EmployeeTestBuilder.anEmployee().withUserName("ASFLead").withRole(Role.ASFLead).build();
        portfolioManager = EmployeeTestBuilder.anEmployee().withUserName("PortfolioManager").withRole(Role.PortfolioManager).withManager(aSFLead).build();
        originalManager = EmployeeTestBuilder.anEmployee().withUserName("originalManager").build();
        newManager = EmployeeTestBuilder.anEmployee().withUserName("newManager").build();
        peopleManager = EmployeeTestBuilder.anEmployee().withUserName("PeopleManager").withRole(Role.PeopleManager).withManager(portfolioManager).build();
        employee = EmployeeTestBuilder.anEmployee().withRole(Role.Employee).withManager(peopleManager).build();


        agreement1 = new Agreement(employee, 2017, 1, false, false);
        agreement2 = new Agreement(employee, 2017, 2, false, false);
        agreement3 = new Agreement(employee, 2017, 3, false, false);
        agreement4 = new Agreement(employee, 2017, 4, false, false);
        agreement5 = new Agreement(employee, 2017, 5, false, false);
        agreement6 = new Agreement(employee, 2017, 6, false, false);
        agreement7 = new Agreement(employee, 2017, 7, false, false);
        agreement8 = new Agreement(employee, 2017, 8, false, false);
        agreement9 = new Agreement(employee, 2017, 9, false, false);
        agreement10 = new Agreement(employee, 2017, 10, false, false);
        agreement11 = new Agreement(employee, 2017, 11, false, false);
        agreement12 = new Agreement(employee, 2017, 12, false, false);

        testAgreementListLong = Lists.newArrayList(agreement1, agreement2, agreement3, agreement4, agreement5, agreement6, agreement7, agreement8, agreement9, agreement10, agreement11, agreement12);

        agreementWithManager1 = new Agreement(employee, 2017, 1, false, false);
        agreementWithManager2 = new Agreement(employee, 2017, 2, false, false);
        agreementWithManager3 = new Agreement(employee, 2017, 3, false, false);
        agreementWithManager4 = new Agreement(employee, 2017, 4, false, false);
        agreementWithManager5 = new Agreement(employee, 2017, 5, false, false);
        agreementWithManager6 = new Agreement(employee, 2017, 6, false, false);
        agreementWithManager7 = new Agreement(employee, 2017, 7, false, false);
        agreementWithManager8 = new Agreement(employee, 2017, 8, false, false);
        agreementWithManager9 = new Agreement(employee, 2017, 9, false, false);
        agreementWithManager10 = new Agreement(employee, 2017, 10, false, false);
        agreementWithManager11 = new Agreement(employee, 2017, 11, false, false);
        agreementWithManager12 = new Agreement(employee, 2017, 12, false, false);

        agreementWithManager1.setManager(originalManager);
        agreementWithManager2.setManager(originalManager);
        agreementWithManager3.setManager(originalManager);
        agreementWithManager4.setManager(originalManager);
        agreementWithManager5.setManager(originalManager);
        agreementWithManager6.setManager(originalManager);
        agreementWithManager7.setManager(originalManager);
        agreementWithManager8.setManager(originalManager);
        agreementWithManager9.setManager(originalManager);
        agreementWithManager10.setManager(originalManager);
        agreementWithManager11.setManager(originalManager);
        agreementWithManager12.setManager(originalManager);
        testAgreementListLongWithManager = Lists.newArrayList(agreementWithManager1, agreementWithManager2, agreementWithManager3, agreementWithManager4, agreementWithManager5, agreementWithManager6, agreementWithManager7, agreementWithManager8, agreementWithManager9, agreementWithManager10, agreementWithManager11, agreementWithManager12);


        emptyTestAgreementList = Lists.newArrayList();

        agreementDTO1 = new AgreementDTO(1, "Nicky", "Len", true, false, new AgreementDate(2017, 11));
        agreementDTO2 = new AgreementDTO(2, "Nicky", "Len", true, false, new AgreementDate(2017, 12));
        testAgreementDTOListShort = Lists.newArrayList(agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1, agreementDTO1);
        testAgreementDTOListEmpty = new ArrayList<>();

        reverseConfirmationDTO = new ReverseConfirmationDTO("username", "username", "MAR");

//        ReflectionTestUtils.setField(agreementService, "clock", Clock
//                .fixed(LocalDateTime.of(2017, 3, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 3, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
    }


    @Test
    public void createNewAgreementsForEmployee_shouldReturn12NewAgreementsBasedOnThatEmployee() throws Exception {
        employee.setManager(originalManager);
        assertThat(agreementService.createNewAgreementsForEmployee(employee)).usingFieldByFieldElementComparator()
                .containsAll(testAgreementListLongWithManager);


    }

    @Test
    public void setCurrentManagerOnFutureAgreements_shouldReturnAgreementsWithNewManagerForFutureMonths() throws Exception {
        TimeManager.setClock(Clock.fixed(
                LocalDateTime.of(2017, 6, 6, 0, 0).toInstant(OffsetDateTime.now().getOffset()),
                Clock.systemDefaultZone().getZone()));
        List<Agreement> agreementList = new ArrayList<>();
        agreementList.add(agreement5);
        agreementList.add(agreement6);
        agreementList.add(agreement7);
        agreementList.add(agreement8);
        employee.setManager(newManager);

        agreementService.setCurrentManagerOnFutureAgreements(agreementList);

        assertThat(agreement5.getManager()).isNull();
        assertThat(agreement6.getManager()).isNull();
        assertThat(agreement7.getManager()).isEqualTo(newManager);
        assertThat(agreement8.getManager()).isEqualTo(newManager);
    }

    @Test
    public void findThisAgreementsByUserName_shouldReturnListOfEmployeesAgreements() {
        employee.setManager(originalManager);
        agreementService.createNewAgreementsForEmployee(employee);
        when(agreementRepository.findByEmployeeAndYear(employee, 2017)).thenReturn(testAgreementListLong);
        when(employeeRepository.findByUserName("username")).thenReturn(employee);
        when(agreementMapper.mapAgreementListtoDTOList(testAgreementListLong)).thenReturn(testAgreementDTOListShort);

        assertThat(agreementService.findAgreementsByUsername("username"))
                .isEqualTo(testAgreementDTOListShort);
    }

    @Test
    public void findThisAgreementsByUserName_whenNoAgreementsPresent_shouldReturnNewListOfEmployeesAgreements() {
        employee.setManager(originalManager);
        agreementService.createNewAgreementsForEmployee(employee);
        when(agreementRepository.findByEmployeeAndYear(employee, 2017)).thenReturn(emptyTestAgreementList);
        when(employeeRepository.findByUserName("username")).thenReturn(employee);
        when(agreementMapper.mapAgreementListtoDTOList(refEq(testAgreementListLong))).thenReturn(testAgreementDTOListShort);

        assertThat(agreementService.findAgreementsByUsername("username"))
                .isEqualTo(testAgreementDTOListShort);
    }

    @Test
    public void reverseEvalueeConfirmationWithinDeadlineByEvaluee_shouldSetEvalueeConfirmation() {
        reverseConfirmationMocks(reverseConfirmationDTO, employee, employee);

        agreementService.reverseConfirmation(reverseConfirmationDTO);
        Assertions.assertThat(agreement3.getEvalueeConfirmation()).isTrue();
    }

    @Test
    public void reverseConfirmationPastDeadlineByEvaluee_shouldNotSetEvalueeConfirmation() {
        reverseConfirmationMocks(reverseConfirmationDTO, employee, employee);

        agreementService.reverseConfirmation(reverseConfirmationDTO);
        Assertions.assertThat(agreement2.getEvalueeConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersPMAndEmployee_isEvaluatorShouldReturnTrue() {
        reverseEvaluatorConfirmationtest_givenParameters( peopleManager,employee, "MAR",agreement3,true);
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersPortMAndPM_isEvaluatorShouldReturnTrue() {
        ReverseConfirmationDTO portfolioManagerDTO = new ReverseConfirmationDTO(portfolioManager.getUserName(), peopleManager.getUserName(), "MAR");
        reverseConfirmationMocks(portfolioManagerDTO, portfolioManager, peopleManager);
        agreementService.reverseConfirmation(portfolioManagerDTO);
        assertThat(agreement3.getEvaluatorConfirmation()).isTrue();
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersASFLeadAndPortM_isEvaluatorShouldReturnIsTrue() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), portfolioManager.getUserName(), "MAR");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, portfolioManager);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement3.getEvaluatorConfirmation()).isTrue();
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersPortMAndEmployee_isEvaluatorShouldReturnFalse() {
        ReverseConfirmationDTO portfolioManagerDTO = new ReverseConfirmationDTO(portfolioManager.getUserName(), employee.getUserName(), "MAR");
        reverseConfirmationMocks(portfolioManagerDTO, portfolioManager, employee);
        agreementService.reverseConfirmation(portfolioManagerDTO);
        assertThat(agreement3.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersASFLeadAndEmployee_isEvaluatorShouldReturnFalse() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), employee.getUserName(), "MAR");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, employee);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement3.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationWithinDeadline_withParametersASFLeadAndPM_isEvaluatorShouldReturnFalse() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), peopleManager.getUserName(), "MAR");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, peopleManager);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement3.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersPMAndEmployee_isEvaluatorShouldReturnTrue_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(peopleManager.getUserName(), employee.getUserName(), "FEB");
        reverseConfirmationMocks(peopleManagerDTO, peopleManager, employee);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersPortMAndEmployee_isEvaluatorShouldReturnFalse_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO portfolioManagerDTO = new ReverseConfirmationDTO(portfolioManager.getUserName(), employee.getUserName(), "FEB");
        reverseConfirmationMocks(portfolioManagerDTO, portfolioManager, employee);
        agreementService.reverseConfirmation(portfolioManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersPortMAndPM_isEvaluatorShouldReturnTrue_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO portfolioManagerDTO = new ReverseConfirmationDTO(portfolioManager.getUserName(), peopleManager.getUserName(), "FEB");
        reverseConfirmationMocks(portfolioManagerDTO, portfolioManager, peopleManager);
        agreementService.reverseConfirmation(portfolioManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersASFLeadAndEmployee_isEvaluatorShouldReturnFalse_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), employee.getUserName(), "FEB");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, employee);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersASFLeadAndPM_isEvaluatorShouldReturnFalse_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), peopleManager.getUserName(), "FEB");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, peopleManager);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

    @Test
    public void reverseConfirmationPastDeadline_withParametersASFLeadAndPortM_isEvaluatorShouldReturnIsTrue_evaluatorConfirmationShouldNotBeChanged() {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(aSFLead.getUserName(), portfolioManager.getUserName(), "FEB");
        reverseConfirmationMocks(peopleManagerDTO, aSFLead, portfolioManager);
        agreementService.reverseConfirmation(peopleManagerDTO);
        assertThat(agreement2.getEvaluatorConfirmation()).isFalse();
    }

//    @Test
//    public void reverseConfirmationWithinDeadlineMAR_givenIsEvaluator_shouldChangeConfirmation() {
//        reverseEvaluatorConfirmationtest_givenParameters(peopleManager, employee, "MAR", agreement3, true);
//        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, peopleManager, "MAR", agreement3, true);
//        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, portfolioManager, "MAR", agreement3, true);
//    }


    @Test
    public void reverseConfirmationWithinDeadlineMAR_givenIsNotEvaluator_shouldNotChangeConfirmation() {
        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, employee, "MAR", agreement3, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, employee, "MAR", agreement3, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, peopleManager, "MAR", agreement3, false);
    }

    @Test
    public void reverseConfirmationPastDeadlineJUN_givenIsEvaluator_shouldNotChangeConfirmation() {
        reverseEvaluatorConfirmationtest_givenParameters(peopleManager, employee, "JUN", agreement6, false);
        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, peopleManager, "JUN", agreement6, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, portfolioManager, "JUN", agreement6, false);
    }

    @Test
    public void reverseConfirmationPastDeadlineFEB_givenIsEvaluator_shouldNotChangeConfirmation() {
        reverseEvaluatorConfirmationtest_givenParameters(peopleManager, employee, "FEB", agreement2, false);
        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, peopleManager, "FEB", agreement2, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, portfolioManager, "FEB", agreement2, false);
    }

    @Test
    public void reverseConfirmationPastDeadlineJUN_givenIsNotEvaluator_shouldStillNotChangeConfirmation() {
        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, employee, "JUN", agreement6, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, employee, "JUN", agreement6, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, peopleManager, "JUN", agreement6, false);
    }

    @Test
    public void reverseConfirmationPastDeadlineFEB_givenIsNotEvaluator_shouldStillNotChangeConfirmation() {
        reverseEvaluatorConfirmationtest_givenParameters(portfolioManager, employee, "FEB", agreement2, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, employee, "FEB", agreement2, false);
        reverseEvaluatorConfirmationtest_givenParameters(aSFLead, peopleManager, "FEB", agreement2, false);
    }

    private void reverseConfirmationMocks(ReverseConfirmationDTO reverseConfirmationDTO, Employee evaluator, Employee evaluee) {
        when(employeeRepository.findByUserName(reverseConfirmationDTO.evaluator)).thenReturn(evaluator);
        ReflectionTestUtils.setField(evaluator, "id", 45);
        when(employeeRepository.findByUserName(reverseConfirmationDTO.evaluee)).thenReturn(evaluee);
        ReflectionTestUtils.setField(evaluee, "id", 46);
        when(agreementRepository.findByEmployeeAndYear(evaluator, 2017)).thenReturn(testAgreementListLong);
        when(agreementRepository.findByEmployeeAndYear(evaluee, 2017)).thenReturn(testAgreementListLong);
    }

    private void reverseEvaluatorConfirmationtest_givenParameters(Employee evaluator, Employee evaluee, String period, Agreement agreementTotest, Boolean expectedBoolean) {
        ReverseConfirmationDTO peopleManagerDTO = new ReverseConfirmationDTO(evaluator.getUserName(), evaluee.getUserName(), period);
        reverseConfirmationMocks(peopleManagerDTO, evaluator, evaluee);
        agreementService.reverseConfirmation(peopleManagerDTO);
        if (expectedBoolean) {
            assertThat(agreementTotest.getEvaluatorConfirmation()).isTrue();
        } else {
            assertThat(agreementTotest.getEvaluatorConfirmation()).isFalse();
        }
    }
}


