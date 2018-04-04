package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.domain.qscores.QscoreService;
import com.cegeka.evaluations.domain.qscores.UpdateQscoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/qscore")
public class QscoreController {

    @Autowired
    private QscoreService qscoreService;

    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
    public List<QscoreDTO> findQscoresByUsername(@PathVariable String userName){
        return qscoreService.findQScoresByUserName(userName);
    }

    @Secured("ROLE_PEOPLE_MANAGER")
    @RequestMapping(value = "/updatescore", method = RequestMethod.PUT, consumes = "application/json")
    public void updateQscore (@RequestBody UpdateQscoreDTO updateQscoreDTO){
        qscoreService.updateQscore(updateQscoreDTO);
    }
}
