package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.employee.Role;
import com.cegeka.evaluations.infrastructure.clock.TimeManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.*;
import java.util.*;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QscoreServiceTest {

    private Employee testEmployee, testManager;
    private Qscore testQscore1;
    private Qscore testQscore2;
    private Qscore testQscore3;
    private Qscore testQscore4;
    private List<Qscore> testQscoreList;
    private List<QscoreDTO> testQscoreDTOList;
    private ArrayList<Qscore> emptyQtestQscoreList;
    private UpdateQscoreDTO updateQscoreDTO;


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private QscoreService qscoreService;

    @Mock
    private QscoreRepository qscoreRepository;

    @Mock
    private QscoreMapper qscoreMapper;

    @Mock
    private EmployeeRepository employeeRepository;


    @Before
    public void setUp() {
        testManager = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.PeopleManager).build();
        testEmployee = EmployeeTestBuilder.anEmployee().withUserName("leka").withManager(testManager).build();

        testQscore1 = new Qscore(testEmployee, testManager, 2017, 1, 3);
        testQscore2 = new Qscore(testEmployee, testManager, 2017, 2, 3);
        testQscore3 = new Qscore(testEmployee, testManager, 2017, 3, 3);

        testQscoreList = new ArrayList<>();
        testQscoreList.add(testQscore1);
        testQscoreList.add(testQscore2);
        testQscoreList.add(testQscore3);

        emptyQtestQscoreList = new ArrayList<>();

        testQscoreDTOList = new ArrayList<>();
        testQscoreDTOList.add(new QscoreDTO(1, testEmployee, testManager, 2017, 1, 3, "testje"));
        testQscoreDTOList.add(new QscoreDTO(2, testEmployee, testManager, 2017, 2, 3, "ne goeie werker"));
        testQscoreDTOList.add(new QscoreDTO(3, testEmployee, testManager, 2017, 3, 3, "test test test test test"));
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 2, 2, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
        updateQscoreDTO = new UpdateQscoreDTO("seppeg", "leka", "Q1", 5, "test");
    }

    @Test
    public void findQScoresByUserName_shouldReturnQscoreDTOList() {
        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(qscoreMapper.mapQscoreListToDTOList(testQscoreList)).thenReturn(testQscoreDTOList);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        assertThat(qscoreService.findQScoresByUserName("leka")).isEqualTo(testQscoreDTOList);
    }


    @Test
    public void findQScoresByUserNameWithEmptyList_shouldReturnNewQscoreDTOList() {
        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(emptyQtestQscoreList);
        when(qscoreMapper.mapQscoreListToDTOList(refEq(testQscoreList))).thenReturn(testQscoreDTOList);

        assertThat(qscoreService.findQScoresByUserName("leka")).isEqualTo(testQscoreDTOList);
    }

//    @Test
//    public void updateQscoreComment_ShouldUpdateQscoreCommentValue() {
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(employeeRepository.findByUserName("seppeg")).thenReturn(testManager);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQscoreCommentDTO);
//        assertThat(testQscore1.getComment()).isEqualTo("comment1");
//    }

//    @Test
//    public void updateQscoreCommentWhenQuadrimesterOneDeadlineHasPassed_ShouldNotUpdateQscoreCommentValue() {
//        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 12, 9, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
//
//        UpdateQscoreCommentDTO updateQ1scoreCommentDTO = new UpdateQscoreCommentDTO("seppeg", "leka", "Q1", "comment1");
//
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQ1scoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

//    @Test
//    public void updateQscoreCommentWhenQuadrimesterTwoDeadlineHasPassed_ShouldNotUpdateQscoreCommentValue() {
//        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 12, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
//
//        UpdateQscoreCommentDTO updateQ1scoreCommentDTO = new UpdateQscoreCommentDTO("seppeg", "leka", "Q2", "comment1");
//
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQ1scoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

//    @Test
//    public void updateQscoreCommentWhenQuadrimesterThreeDeadlineHasPassed_ShouldNotUpdateQscoreCommentValue() {
//        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2018, 5, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));
//
//        UpdateQscoreCommentDTO updateQ1scoreCommentDTO = new UpdateQscoreCommentDTO("seppeg", "leka", "Q3", "comment1");
//
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQ1scoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

//    @Test
//    public void updateQscoreComment_ShouldNotUpdateQscoreCommentValue_WhenManagerIsPortfolioManagerAndEvalueeIsEmployee() {
//        Employee portfoliomanager = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.PortfolioManager).build();
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(employeeRepository.findByUserName("seppeg")).thenReturn(portfoliomanager);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQscoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

//    @Test
//    public void updateQscoreComment_ShouldNotUpdateQscoreCommentValue_WhenManagerIsAsfleadAndEvalueeIsEmployee() {
//        Employee asfLead = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.ASFLead).build();
//        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
//        when(employeeRepository.findByUserName("seppeg")).thenReturn(asfLead);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQscoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

//    @Test
//    public void updateQscoreComment_ShouldNotUpdateQscoreCommentValue_WhenManagerIsAsfleadAndEvalueeIsPeopleManager() {
//        Employee asfLead = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.ASFLead).build();
//        when(employeeRepository.findByUserName("leka")).thenReturn(testManager);
//        when(employeeRepository.findByUserName("seppeg")).thenReturn(asfLead);
//        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);
//
//        qscoreService.updateQscoreComment(updateQscoreCommentDTO);
//
//        assertThat(testQscore1.getComment()).isNull();
//    }

    @Test
    public void updateQscore_ShouldUpdateQscoreValue() {
        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(employeeRepository.findByUserName("seppeg")).thenReturn(testManager);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQscoreDTO);
        assertThat(testQscore1.getScore()).isEqualTo(5);
    }

    @Test
    public void updateQscoreWhenQuadrimesterOneDeadlineHasPassed_ShouldNotUpdateQscoreValue() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 12, 9, 10, 10).toInstant(UTC), ZoneId.systemDefault()));

        UpdateQscoreDTO updateQ1scoreDTO = new UpdateQscoreDTO("seppeg", "leka", "Q1", 5, "test");

        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQ1scoreDTO);

        assertThat(testQscore1.getScore()).isEqualTo(3);
    }

    @Test
    public void updateQscoreWhenQuadrimesterTwoDeadlineHasPassed_ShouldNotUpdateQscoreValue() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2017, 12, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));

        UpdateQscoreDTO updateQ2scoreDTO = new UpdateQscoreDTO("seppeg", "leka", "Q2", 5, "test");

        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQ2scoreDTO);

        assertThat(testQscore2.getScore()).isEqualTo(3);
    }

    @Test
    public void updateQscoreWhenQuadrimesterThreeDeadlineHasPassed_ShouldNotUpdateQscoreValue() {
        TimeManager.setClock(Clock.fixed(LocalDateTime.of(2018, 5, 16, 10, 10).toInstant(UTC), ZoneId.systemDefault()));

        UpdateQscoreDTO updateQ3scoreDTO = new UpdateQscoreDTO("seppeg", "leka", "Q3", 5, "test");

        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQ3scoreDTO);

        assertThat(testQscore3.getScore()).isEqualTo(3);
    }

    @Test
    public void updateQscore_ShouldNotUpdateQscoreValue_WhenManagerIsPortfolioManagerAndEvalueeIsEmployee() {
        Employee portfoliomanager = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.PortfolioManager).build();
        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(employeeRepository.findByUserName("seppeg")).thenReturn(portfoliomanager);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQscoreDTO);

        assertThat(testQscore1.getScore()).isEqualTo(3);
    }

    @Test
    public void updateQscore_ShouldNotUpdateQscoreValue_WhenManagerIsAsfleadAndEvalueeIsEmployee() {
        Employee asfLead = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.ASFLead).build();
        when(employeeRepository.findByUserName("leka")).thenReturn(testEmployee);
        when(employeeRepository.findByUserName("seppeg")).thenReturn(asfLead);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQscoreDTO);

        assertThat(testQscore1.getScore()).isEqualTo(3);
    }

    @Test
    public void updateQscore_ShouldNotUpdateQscoreValue_WhenManagerIsAsfleadAndEvalueeIsPeopleManager() {
        Employee asfLead = EmployeeTestBuilder.anEmployee().withUserName("seppeg").withRole(Role.ASFLead).build();
        when(employeeRepository.findByUserName("leka")).thenReturn(testManager);
        when(employeeRepository.findByUserName("seppeg")).thenReturn(asfLead);
        when(qscoreRepository.findByEmployeeAndYear(testEmployee, 2017)).thenReturn(testQscoreList);

        qscoreService.updateQscore(updateQscoreDTO);

        assertThat(testQscore1.getScore()).isEqualTo(3);
    }
}