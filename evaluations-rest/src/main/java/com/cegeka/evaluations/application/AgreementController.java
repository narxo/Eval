package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementService;
import com.cegeka.evaluations.domain.agreement.ReverseConfirmationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/agreement")
public class AgreementController {

    @Autowired
    private AgreementService agreementService;

    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
    public List<AgreementDTO> findAgreementsByUsername(@PathVariable String userName) {
        return agreementService.findAgreementsByUsername(userName);
    }
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/updateAgreement", method = RequestMethod.POST, consumes = "application/json")
    public void updateAgreement(@RequestBody AgreementDTO agreementDTO) {
        agreementService.updateAgreement(agreementDTO);
    }
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/reverseConfirmation", method = RequestMethod.PUT)
    public void reverseConfirmation(@RequestBody ReverseConfirmationDTO reverseConfirmationDTO) {
        agreementService.reverseConfirmation(reverseConfirmationDTO);
    }
}
