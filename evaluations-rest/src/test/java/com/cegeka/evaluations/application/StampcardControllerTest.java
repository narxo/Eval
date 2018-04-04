package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.agreement.Agreement;
import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementDate;
import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.domain.stampcard.StampCardInputDTO;
import com.cegeka.evaluations.domain.stampcard.StampcardMapper;
import com.cegeka.evaluations.domain.stampcard.StampcardService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StampcardControllerTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private StampcardController stampcardController;

    @InjectMocks
    private StampcardMapper stampcardMapper;

    @Mock
    private StampcardService stampcardService;



    public static final String NICKY = "Nicky";
    public static final String LEN = "Len";
    public static final String USER_NAME = "leka";
    private Employee testManager;
    private Employee testEmployee;
    private QscoreDTO qscoreDTO1;
    private QscoreDTO qscoreDTO2;
    private QscoreDTO qscoreDTO3;
    private QscoreDTO qscoreDTO4;
    private AgreementDTO agreementDTO1;
    private AgreementDTO agreementDTO2;
    private AgreementDTO agreementDTO3;
    private AgreementDTO agreementDTO4;
    private AgreementDTO agreementDTO5;
    private AgreementDTO agreementDTO6;
    private AgreementDTO agreementDTO7;
    private AgreementDTO agreementDTO8;
    private AgreementDTO agreementDTO9;
    private AgreementDTO agreementDTO10;
    private AgreementDTO agreementDTO11;
    private AgreementDTO agreementDTO12;
    private List<AgreementDTO> agreementDtoList;
    private List<QscoreDTO> qscoreDtoList;
    private List<StampCardInputDTO> stampcardInputDtoList;


    @Before
    public void setUp() throws Exception {
        testManager = EmployeeTestBuilder.anEmployee().build();
        testEmployee = EmployeeTestBuilder.anEmployee().withUserName(USER_NAME).withManager(testManager).build();
        qscoreDTO1 = new QscoreDTO(1, testEmployee, testManager, 2017, 1, 3);
        qscoreDTO2 = new QscoreDTO(2, testEmployee, testManager, 2017, 2, 3);
        qscoreDTO3 = new QscoreDTO(3, testEmployee, testManager, 2017, 3, 3);
        qscoreDTO4 = new QscoreDTO(4, testEmployee, testManager, 2017, 4, 3);
        agreementDTO1 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 1));
        agreementDTO2 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 2));
        agreementDTO3 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 3));
        agreementDTO4 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 4));
        agreementDTO5 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 5));
        agreementDTO6 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 6));
        agreementDTO7 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 7));
        agreementDTO8 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 8));
        agreementDTO9 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 9));
        agreementDTO10 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 10));
        agreementDTO11 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 11));
        agreementDTO12 = new AgreementDTO(1, NICKY, LEN, true, false, new AgreementDate(2017, 12));
        agreementDtoList = Arrays.asList(agreementDTO1, agreementDTO2, agreementDTO3, agreementDTO4, agreementDTO5, agreementDTO6,agreementDTO7, agreementDTO8, agreementDTO9, agreementDTO10, agreementDTO11, agreementDTO12);
        qscoreDtoList = Arrays.asList(qscoreDTO1,qscoreDTO2,qscoreDTO3, qscoreDTO4);
        stampcardInputDtoList = stampcardMapper.mapAgreementsAndQscoresToAStampcard(agreementDtoList,qscoreDtoList);

    }

    @Test
    public void findStampCardByUsername_whenCalled_shouldReturnStampcard() {
        when(stampcardService.findStampcardByUsername(USER_NAME)).thenReturn(stampcardInputDtoList);
        assertThat(stampcardController.findStampcardByUsername(USER_NAME)).usingFieldByFieldElementComparator().containsAll(stampcardInputDtoList);
    }
}