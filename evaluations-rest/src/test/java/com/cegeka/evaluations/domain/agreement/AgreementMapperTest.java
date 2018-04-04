package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AgreementMapperTest {
    private Employee david,manager;
    private Agreement agreement;
    private AgreementMapper agreementMapper;

    @Before
    public void setUp() throws Exception {
        david = EmployeeTestBuilder.david().build();
        manager = EmployeeTestBuilder.xan().build();
        agreement = new Agreement(1, david, manager, 2017, 1, false, true);
        agreementMapper = new AgreementMapper();
    }

    @Test
    public void mapAgreementToDTO_shouldReturnDTOWithSameValuesAsAgreementObject() throws Exception {

        Assertions.assertThat(agreementMapper.mapAgreementToDTO(agreement))
                .isEqualToComparingFieldByFieldRecursively(new AgreementDTO(1, "Xan Vranckaert", "David Scheers", false, true, new AgreementDate(2017, 1)));
    }

    @Test
    public void mapAgreementListToDTOList_shouldReturnListOfDtosWithSameValuesOfAgreements() {

        AgreementDTO agreementDTO = new AgreementDTO(1, "Xan Vranckaert", "David Scheers", false, true, new AgreementDate(2017, 1));

        List<Agreement> testListOfAgreements = Lists.newArrayList(agreement);
        List<AgreementDTO> testListOfAgreementDTOs = Lists.newArrayList(agreementDTO);


        Assertions.assertThat(agreementMapper.mapAgreementListtoDTOList(testListOfAgreements)
                .containsAll(testListOfAgreementDTOs));
    }
}