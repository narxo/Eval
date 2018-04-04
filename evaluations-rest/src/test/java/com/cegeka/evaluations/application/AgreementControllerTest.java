package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.agreement.AgreementDTO;
import com.cegeka.evaluations.domain.agreement.AgreementService;
import com.cegeka.evaluations.domain.agreement.ReverseConfirmationDTO;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AgreementControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private AgreementController agreementController;

    @Mock
    private AgreementService agreementService;

    private List<AgreementDTO> agreementDTOList;
    private AgreementDTO agreementDTO;
    private ReverseConfirmationDTO reverseConfirmationDTO;

    @Before
    public void setUp() throws Exception {
        agreementDTO = new AgreementDTO();
        agreementDTOList = Lists.newArrayList(agreementDTO);
        reverseConfirmationDTO = new ReverseConfirmationDTO();
    }

    @Test
    public void findAgreementsByUsername() throws Exception {
        when(agreementService.findAgreementsByUsername("username")).thenReturn(agreementDTOList);
        Assertions.assertThat(agreementController.findAgreementsByUsername("username")).isEqualTo(agreementDTOList);
    }


    @Test
    public void updateAgreement() throws Exception {
        agreementController.updateAgreement(agreementDTO);
        verify(agreementService).updateAgreement(agreementDTO);
    }

    @Test
    public void reverseConfirmation_ShouldInvokeAgreementService() {
        agreementController.reverseConfirmation(reverseConfirmationDTO);
        verify(agreementService).reverseConfirmation(reverseConfirmationDTO);
    }
}