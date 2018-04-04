package com.cegeka.evaluations.security;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBGrantedAuthoritiesMapper implements org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        Employee employee = employeeRepository.findByUserName(username.toLowerCase());

        return employee.getRole().getSecurityRoles()
                .stream()
                .map(securityRole -> new SimpleGrantedAuthority(securityRole))
                .collect(Collectors.toList());
    }
}
