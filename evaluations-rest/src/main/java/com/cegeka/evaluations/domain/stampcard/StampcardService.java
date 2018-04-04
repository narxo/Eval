package com.cegeka.evaluations.domain.stampcard;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementService;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.domain.qscores.QscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StampcardService {
    @Autowired
    QscoreService qscoreService;

    @Autowired
    AgreementService agreementService;
    @Autowired
    StampcardMapper stampcardMapper;

    @Transactional
    public List<StampCardInputDTO> findStampcardByUsername(String userName) {
        List<AgreementDTO> agreementDTOList = agreementService.findAgreementsByUsername(userName.toLowerCase());
        List<QscoreDTO> qscoreDTOList = qscoreService.findQScoresByUserName(userName.toLowerCase());
        return stampcardMapper.mapAgreementsAndQscoresToAStampcard(agreementDTOList, qscoreDTOList);
    }

    @Transactional
    public List<StampCardInputDTO> findStampcardByUsernameAndYear(String userName, int year) {
        List<AgreementDTO> agreementDTOList = agreementService.findAgreementsByUsernameAndYear(userName.toLowerCase(), year);
        List<QscoreDTO> qscoreDTOList = qscoreService.findQScoresByUserNameAndYear(userName.toLowerCase(), year);
        return stampcardMapper.mapAgreementsAndQscoresToAStampcard(agreementDTOList, qscoreDTOList);
    }
}

