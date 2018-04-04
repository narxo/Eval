package com.cegeka.evaluations.application;


import com.cegeka.evaluations.domain.stampcard.StampCardInputDTO;
import com.cegeka.evaluations.domain.stampcard.StampcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/stampcard")
public class StampcardController {

    @Autowired
    private StampcardService stampcardService;

    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{userName}/{year}", method = RequestMethod.GET, produces = "application/json")
    public List<StampCardInputDTO> findStampcardByUsername(@PathVariable(value = "userName") String userName, @PathVariable(value = "year") int year){
        return stampcardService.findStampcardByUsernameAndYear(userName, year);
    }
}
