package com.cegeka.evaluations.infrastructure.configuration;

import com.cegeka.evaluations.domain.populateDB.LdapAttributesMapper;
import com.cegeka.evaluations.domain.populateDB.LdapEmployeeDTO;
import com.cegeka.evaluations.domain.populateDB.LdapQuerier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prd")
public class LdapQuerierFromProduction implements LdapQuerier {

    @Autowired
    private ContextSource contextSource;

    @Override
    public List<LdapEmployeeDTO> getAllEmployeesFromLdap() {
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
        ldapTemplate.setIgnorePartialResultException(true);

        OrFilter orFilter = new OrFilter();
        orFilter.or(new EqualsFilter("department", "FL Agile Software Factory"));
        orFilter.or(new EqualsFilter("department", "FL Applications Public"));
        orFilter.or(new EqualsFilter("department", "FL Applications Private"));

        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectCategory", "person"));
        andFilter.and(new EqualsFilter("objectClass", "user"));
        andFilter.and(orFilter);

        return ldapTemplate.search("", andFilter.encode(), new LdapAttributesMapper());
    }
}
