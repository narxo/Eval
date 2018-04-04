package com.cegeka.evaluations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@Service
@Profile("!prd")
public class LdapAuthenticatorDev {

    @Autowired
    private DBGrantedAuthoritiesMapper dbGrantedAuthoritiesMapper;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Allow for login in as test users
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("uid={0}")
                .ldapAuthoritiesPopulator(dbGrantedAuthoritiesMapper)
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordAttribute("userPassword");
    }

}
