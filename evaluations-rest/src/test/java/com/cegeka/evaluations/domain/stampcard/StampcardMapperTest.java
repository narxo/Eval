package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.builders.StampcardDTOsWithListForTest;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static com.cegeka.evaluations.domain.builders.AgreementDTOsWithListForTest.*;
import static com.cegeka.evaluations.domain.builders.QscoreDTOsWithListForTest.*;

public class StampcardMapperTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private StampcardMapper stampcardMapper;

    private List<AgreementDTO> agreementDtoList;
    private List<QscoreDTO> qscoreDtoList;

    private StampCardInputDTO stampcardInputDTO1, stampcardInputDTO2, stampcardInputDTO3, stampcardInputDTO4, stampcardInputDTO5, stampcardInputDTO6, stampcardInputDTO7, stampcardInputDTO8, stampcardInputDTO9,
            stampcardInputDTO10, stampcardInputDTO11, stampcardInputDTO12, stampcardInputDTO13, stampcardInputDTO14, stampcardInputDTO15, stampcardInputDTO16;
    private List<StampCardInputDTO> stampcardInputDTOList;

    @Before
    public void setUp() throws Exception {
        agreementDtoList = return12AgreementsDTOList();
        qscoreDtoList =return3QuadrimesterListforTest();

        stampcardInputDTO1 = new StampCardInputDTO(testAgreementDto1JAN);
        stampcardInputDTO2 = new StampCardInputDTO(testAgreementDto2FEB);
        stampcardInputDTO3 = new StampCardInputDTO(testAgreementDto3MAR);
        stampcardInputDTO4 = new StampCardInputDTO(testAgreementDto4APR);
        stampcardInputDTO5 = new StampCardInputDTO(testQscoreDTOQ1);
        stampcardInputDTO6 = new StampCardInputDTO(testAgreementDto5MAY);
        stampcardInputDTO7 = new StampCardInputDTO(testAgreementDto6JUN);
        stampcardInputDTO8 = new StampCardInputDTO(testAgreementDto7JUL);
        stampcardInputDTO9 = new StampCardInputDTO(testAgreementDto8AUG);
        stampcardInputDTO10 = new StampCardInputDTO(testQscoreDTOQ2);
        stampcardInputDTO11 = new StampCardInputDTO(testAgreementDto9SEP);
        stampcardInputDTO12 = new StampCardInputDTO(testAgreementDto10OCT);
        stampcardInputDTO13 = new StampCardInputDTO(testAgreementDto11NOV);
        stampcardInputDTO14 = new StampCardInputDTO(testAgreementDto12DEC);
        stampcardInputDTO15 = new StampCardInputDTO(testQscoreDTOQ3);

        stampcardInputDTOList = Arrays.asList(stampcardInputDTO1, stampcardInputDTO2, stampcardInputDTO3, stampcardInputDTO4, stampcardInputDTO5, stampcardInputDTO6, stampcardInputDTO7, stampcardInputDTO8, stampcardInputDTO9,stampcardInputDTO10,stampcardInputDTO11
    ,stampcardInputDTO12,stampcardInputDTO13,stampcardInputDTO14,stampcardInputDTO15);}

    @Test
    public void mapAgreementsAndQscoresToAStampcard_shouldReturnTheDesiredStampcard() {
        List<StampCardInputDTO> actual = stampcardMapper.mapAgreementsAndQscoresToAStampcard(agreementDtoList, qscoreDtoList);
        Assertions.assertThat(actual).usingFieldByFieldElementComparator().containsExactly(stampcardInputDTOList.toArray(new StampCardInputDTO[0]));
    }
}