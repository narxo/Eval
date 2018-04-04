package com.cegeka.evaluations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@Service
@Profile("prd")
public class LdapAuthenticatorPrd {

    @Autowired
    private DBGrantedAuthoritiesMapper dbGrantedAuthoritiesMapper;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Allow for real users
        auth.ldapAuthentication()
                .userSearchBase("OU=XXX")
                .userSearchFilter("(&(objectCategory=person)(objectClass=user)(sAMAccountName={0}))")
                .ldapAuthoritiesPopulator(dbGrantedAuthoritiesMapper)
                .contextSource()
                .url("ldap://domain.local/")
                .root("dc=XXXX")
                .managerDn("CN=XXX Internship, XXX, DC=local")
                .managerPassword("XXX")
                .port(389);
    }

}
