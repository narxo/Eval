package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementDate;
import com.cegeka.evaluations.domain.agreement.AgreementService;
import com.cegeka.evaluations.domain.builders.AgreementDTOsWithListForTest;
import com.cegeka.evaluations.domain.builders.QscoreDTOsWithListForTest;
import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.domain.qscores.QscoreService;
import org.assertj.core.api.Assertions;
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

import static com.cegeka.evaluations.domain.builders.AgreementDTOsWithListForTest.*;
import static com.cegeka.evaluations.domain.builders.QscoreDTOsWithListForTest.*;
import static org.mockito.Mockito.*;

public class StampcardServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    public static final String USER_NAME = "leka";

    private List<AgreementDTO> testAgreementDTOList;
    private List<QscoreDTO> testQscoreDTOList;
    private Employee testManager;
    private Employee testEmployee;

    private StampCardInputDTO stampcardInputDTO1, stampcardInputDTO2, stampcardInputDTO3, stampcardInputDTO4, stampcardInputDTO5, stampcardInputDTO6, stampcardInputDTO7, stampcardInputDTO8, stampcardInputDTO9,
            stampcardInputDTO10, stampcardInputDTO11, stampcardInputDTO12, stampcardInputDTO13, stampcardInputDTO14, stampcardInputDTO15, stampcardInputDTO16;
    private List<StampCardInputDTO> stampcardInputDTOList;

    @InjectMocks
    private StampcardService stampcardService;

    @Mock
    private QscoreService qscoreService;

    @Mock
    private AgreementService agreementService;

    @Mock
    StampcardMapper stampcardMapper;

    @Before
    public void setUp() throws Exception {
        testManager = EmployeeTestBuilder.anEmployee().build();
        testEmployee = EmployeeTestBuilder.anEmployee().withUserName(USER_NAME).withManager(testManager).build();
        testQscoreDTOList = return4QuartersListforTest();
        testAgreementDTOList = AgreementDTOsWithListForTest.return12AgreementsDTOList();

        stampcardInputDTO1 = new StampCardInputDTO(testAgreementDto1JAN);
        stampcardInputDTO2 = new StampCardInputDTO(testAgreementDto2FEB);
        stampcardInputDTO3 = new StampCardInputDTO(testAgreementDto3MAR);
        stampcardInputDTO4 = new StampCardInputDTO(testQscoreDTOQ1);
        stampcardInputDTO5 = new StampCardInputDTO(testAgreementDto4APR);
        stampcardInputDTO6 = new StampCardInputDTO(testAgreementDto5MAY);
        stampcardInputDTO7 = new StampCardInputDTO(testAgreementDto6JUN);
        stampcardInputDTO8 = new StampCardInputDTO(testQscoreDTOQ1);
        stampcardInputDTO9 = new StampCardInputDTO(testAgreementDto7JUL);
        stampcardInputDTO10 = new StampCardInputDTO(testAgreementDto8AUG);
        stampcardInputDTO11 = new StampCardInputDTO(testAgreementDto9SEP);
        stampcardInputDTO12 = new StampCardInputDTO(testQscoreDTOQ3);
        stampcardInputDTO13 = new StampCardInputDTO(testAgreementDto10OCT);
        stampcardInputDTO14 = new StampCardInputDTO(testAgreementDto11NOV);
        stampcardInputDTO15 = new StampCardInputDTO(testAgreementDto12DEC);
        stampcardInputDTO16 = new StampCardInputDTO(testQscoreDTOQ4);

        stampcardInputDTOList = Arrays.asList(stampcardInputDTO1, stampcardInputDTO2, stampcardInputDTO3, stampcardInputDTO4, stampcardInputDTO5, stampcardInputDTO6, stampcardInputDTO7, stampcardInputDTO8, stampcardInputDTO9,
                stampcardInputDTO10, stampcardInputDTO11, stampcardInputDTO12, stampcardInputDTO13, stampcardInputDTO14, stampcardInputDTO15, stampcardInputDTO16);
    }

    @Test
    public void findStampcardByUsername_whenCalled_shouldReturnCombinedList() {
        when(agreementService.findAgreementsByUsername(USER_NAME)).thenReturn(testAgreementDTOList);
        when(qscoreService.findQScoresByUserName(USER_NAME)).thenReturn(testQscoreDTOList);
        when(stampcardMapper.mapAgreementsAndQscoresToAStampcard(testAgreementDTOList, testQscoreDTOList)).thenReturn(stampcardInputDTOList);
        Assertions.assertThat(stampcardService.findStampcardByUsername(USER_NAME)).isEqualTo(stampcardInputDTOList);
    }
}