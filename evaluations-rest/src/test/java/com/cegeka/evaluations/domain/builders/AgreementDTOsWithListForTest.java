package com.cegeka.evaluations.domain.builders;

import java.util.List;
import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementDate;
import org.assertj.core.util.Lists;


public class AgreementDTOsWithListForTest {
   public static List<AgreementDTO> agreementDTOList = Lists.newArrayList();
   public static final AgreementDTO testAgreementDto1JAN = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 1));
   public static final AgreementDTO testAgreementDto2FEB = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 2));
   public static final AgreementDTO testAgreementDto3MAR = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 3));
   public static final AgreementDTO testAgreementDto4APR = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 4));
   public static final AgreementDTO testAgreementDto5MAY = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 5));
   public static final AgreementDTO testAgreementDto6JUN = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 6));
   public static final AgreementDTO testAgreementDto7JUL = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 7));
   public static final AgreementDTO testAgreementDto8AUG = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 8));
   public static final AgreementDTO testAgreementDto9SEP = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 9));
   public static final AgreementDTO testAgreementDto10OCT = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 10));
   public static final AgreementDTO testAgreementDto11NOV = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 11));
   public static final AgreementDTO testAgreementDto12DEC = new AgreementDTO(1, "evaluator", "evalue", true, false, new AgreementDate(2016, 12));


    public static List<AgreementDTO> return12AgreementsDTOList() {
        agreementDTOList.add(testAgreementDto1JAN);
        agreementDTOList.add(testAgreementDto2FEB);
        agreementDTOList.add(testAgreementDto3MAR);
        agreementDTOList.add(testAgreementDto4APR);
        agreementDTOList.add(testAgreementDto5MAY);
        agreementDTOList.add(testAgreementDto6JUN);
        agreementDTOList.add(testAgreementDto7JUL);
        agreementDTOList.add(testAgreementDto8AUG);
        agreementDTOList.add(testAgreementDto9SEP);
        agreementDTOList.add(testAgreementDto10OCT);
        agreementDTOList.add(testAgreementDto11NOV);
        agreementDTOList.add(testAgreementDto12DEC);
        return agreementDTOList;
    }
}
