package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.qscores.Qscore;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StampcardMapper {

    public List<StampCardInputDTO> mapAgreementsAndQscoresToAStampcard(List<AgreementDTO> agreementDTOList, List<QscoreDTO> qscoreDTOList) {
        List<StampCardInputDTO> stampcard = new ArrayList<>();
        int agreementcounter = 0;
        int qscorecounter = 0;
        for (int i = 0; i < 13; i++) {
            if (i != 4) {
                stampcard.add(i, new StampCardInputDTO(agreementDTOList.get(agreementcounter)));
                agreementcounter++;
            } else {
                stampcard.add(i, new StampCardInputDTO(qscoreDTOList.get(qscorecounter)));
                qscorecounter++;
            }
        }
        return stampcard;
    }
}
