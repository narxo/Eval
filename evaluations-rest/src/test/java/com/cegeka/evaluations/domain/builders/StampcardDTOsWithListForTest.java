package com.cegeka.evaluations.domain.builders;

import com.cegeka.evaluations.domain.stampcard.StampCardInputDTO;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

import static com.cegeka.evaluations.domain.builders.AgreementDTOsWithListForTest.*;
import static com.cegeka.evaluations.domain.builders.QscoreDTOsWithListForTest.*;

public class StampcardDTOsWithListForTest {
    public static List<StampCardInputDTO> stampcardInputDTOList  = Lists.newArrayList();;
    public static final StampCardInputDTO stampcardInputDTO1 = new StampCardInputDTO(testAgreementDto1JAN);
    public static final StampCardInputDTO stampcardInputDTO2 = new StampCardInputDTO(testAgreementDto2FEB);
    public static final StampCardInputDTO stampcardInputDTO3 = new StampCardInputDTO(testAgreementDto3MAR);
    public static final StampCardInputDTO stampcardInputDTO4 = new StampCardInputDTO(testQscoreDTOQ1);
    public static final StampCardInputDTO stampcardInputDTO5 = new StampCardInputDTO(testAgreementDto4APR);
    public static final StampCardInputDTO stampcardInputDTO6 = new StampCardInputDTO(testAgreementDto5MAY);
    public static final StampCardInputDTO stampcardInputDTO7 = new StampCardInputDTO(testAgreementDto6JUN);
    public static final StampCardInputDTO stampcardInputDTO8 = new StampCardInputDTO(testQscoreDTOQ2);
    public static final StampCardInputDTO stampcardInputDTO9 = new StampCardInputDTO(testAgreementDto7JUL);
    public static final StampCardInputDTO stampcardInputDTO10 = new StampCardInputDTO(testAgreementDto8AUG);
    public static final StampCardInputDTO stampcardInputDTO11 = new StampCardInputDTO(testAgreementDto9SEP);
    public static final StampCardInputDTO stampcardInputDTO12 = new StampCardInputDTO(testQscoreDTOQ2);
    public static final StampCardInputDTO stampcardInputDTO13 = new StampCardInputDTO(testAgreementDto10OCT);
    public static final StampCardInputDTO stampcardInputDTO14 = new StampCardInputDTO(testAgreementDto11NOV);
    public static final StampCardInputDTO stampcardInputDTO15 = new StampCardInputDTO(testAgreementDto12DEC);
    public static final StampCardInputDTO stampcardInputDTO16 = new StampCardInputDTO(testQscoreDTOQ4);

    public static List<StampCardInputDTO> returnStandardStampcardListforTest() {
       stampcardInputDTOList = Arrays.asList(stampcardInputDTO1, stampcardInputDTO2, stampcardInputDTO3, stampcardInputDTO4, stampcardInputDTO5, stampcardInputDTO6, stampcardInputDTO7, stampcardInputDTO8, stampcardInputDTO9,
                stampcardInputDTO10, stampcardInputDTO11, stampcardInputDTO12, stampcardInputDTO13, stampcardInputDTO14, stampcardInputDTO15, stampcardInputDTO16);
       return stampcardInputDTOList;
    }
}
//