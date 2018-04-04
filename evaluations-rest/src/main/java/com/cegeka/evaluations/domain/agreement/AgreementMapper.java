package com.cegeka.evaluations.domain.agreement;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgreementMapper {

    public List<AgreementDTO> mapAgreementListtoDTOList(List<Agreement> agreementList){
        return   agreementList.stream()
                .map(agreement -> mapAgreementToDTO(agreement))
                .collect(Collectors.toList());
    }

    public AgreementDTO mapAgreementToDTO(Agreement agreement) {
        AgreementDate agreementDate = new AgreementDate(agreement.getYear(), agreement.getMonth());
        AgreementDTO agreementDTO = new AgreementDTO(agreement.getId(), agreement.getManager().getName(), agreement.getEmployee().getName(), agreement.getEvaluatorConfirmation(),agreement.getEvalueeConfirmation(), agreementDate);
        return agreementDTO;
    }
}
