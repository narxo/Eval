package com.cegeka.evaluations.domain.qscores;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class QscoreMapper {

    public List<QscoreDTO> mapQscoreListToDTOList(List<Qscore> qscoreList) {
        return qscoreList.stream()
                .map(qscore -> mapQscoreToDTO(qscore))
                .collect(Collectors.toList());
    }

    public QscoreDTO mapQscoreToDTO(Qscore qscore) {
        QscoreDTO qscoreDTO = new QscoreDTO(qscore.getId(),
                qscore.getEmployee(),
                qscore.getManager(),
                qscore.getYear(),
                qscore.getQuadrimester(),
                qscore.getScore(),
                qscore.getComment());
        return qscoreDTO;
    }
}
