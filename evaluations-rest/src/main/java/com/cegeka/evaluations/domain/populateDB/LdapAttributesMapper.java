package com.cegeka.evaluations.domain.populateDB;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public class LdapAttributesMapper implements AttributesMapper {

    public LdapEmployeeDTO mapFromAttributes(Attributes attributes) throws NamingException {

        LdapEmployeeDTO LDAPEmployee = new LdapEmployeeDTO();
        LDAPEmployee.setCN("CN=" + attributes.get("cn").get().toString());
        LDAPEmployee.setFirstName(attributes.get("givenName").get().toString());
        LDAPEmployee.setLastName(attributes.get("sn").get().toString());
        LDAPEmployee.setUserName(attributes.get("sAMAccountName").get().toString().toLowerCase());

        String manager = attributes.get("manager").get().toString().split(",")[0];
        LDAPEmployee.setManagerCN(manager);

        if (attributes.get("extensionAttribute6") != null) {
            String attributeDifferentiatingPortfolioManagers = attributes.get("extensionAttribute6").get().toString();
            LDAPEmployee.setIsPortfolioManager(attributeDifferentiatingPortfolioManagers.toLowerCase().contains("portfolio management"));
        }

        return LDAPEmployee;
    }
}
