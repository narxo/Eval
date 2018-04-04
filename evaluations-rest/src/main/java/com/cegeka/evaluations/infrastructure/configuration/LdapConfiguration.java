package com.cegeka.evaluations.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://cegekanv.corp.local");
        contextSource.setBase("dc=cegekanv,dc=corp,dc=local");
        contextSource.setUserDn("CN=Cegeka Internship,OU=Service Accounts,OU=BE,OU=Cegeka,DC=cegekanv,DC=corp,DC=local");
        contextSource.setPassword("*4cU^xb3XIy0Kf9");
        return contextSource;
    }

}
