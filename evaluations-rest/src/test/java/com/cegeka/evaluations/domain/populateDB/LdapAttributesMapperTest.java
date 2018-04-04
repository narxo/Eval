package com.cegeka.evaluations.domain.populateDB;

import org.junit.Before;
import org.junit.Test;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class LdapAttributesMapperTest {

    private LdapAttributesMapper ldapAttributesMapper;
    private Attributes attributes;

    @Before
    public void setUp() throws Exception {
        ldapAttributesMapper = new LdapAttributesMapper();
        attributes = new BasicAttributes();
        attributes.put("cn", "Stroobants Jeroen");
        attributes.put("givenName", "Jeroen");
        attributes.put("sn", "Stroobants");
        attributes.put("sAMAccountName", "jeroens");
        attributes.put("manager", "Gielen Seppe");
    }

    @Test
    public void mapFromAttributes_GivenAttribute6_ShouldReturnCorrectLdapEmployeeDTO() throws Exception {
        attributes.put("extensionAttribute6", "portfolio management");

        LdapEmployeeDTO actual = ldapAttributesMapper.mapFromAttributes(attributes);
        LdapEmployeeDTO expected = LDAPEmployeeDTOTestBuilder.anEmployee()
                .withCN("CN=Stroobants Jeroen")
                .withFirstName("Jeroen")
                .withLastName("Stroobants")
                .withUserName("jeroens")
                .withManagerCN("Gielen Seppe")
                .withManagerId(null)
                .build();
        expected.setIsPortfolioManager(true);

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void mapFromAttributes_givenWithoutAttribute6_ShouldReturnCorrectLdapEmployeeDTO() throws Exception {
        LdapEmployeeDTO actual = ldapAttributesMapper.mapFromAttributes(attributes);
        LdapEmployeeDTO expected = LDAPEmployeeDTOTestBuilder.anEmployee()
                .withCN("CN=Stroobants Jeroen")
                .withFirstName("Jeroen")
                .withLastName("Stroobants")
                .withUserName("jeroens")
                .withManagerCN("Gielen Seppe")
                .withManagerId(null)
                .build();
        expected.setIsPortfolioManager(false);

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}