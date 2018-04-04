package com.cegeka.evaluations.domain.employee;

import com.google.common.collect.Sets;

import java.util.Set;

public enum Role {
    Employee(Sets.newHashSet("ROLE_EMPLOYEE")),
    PeopleManager(Sets.newHashSet("ROLE_PEOPLE_MANAGER", "ROLE_EMPLOYEE")),
    PortfolioManager(Sets.newHashSet("ROLE_PORTFOLIO_MANAGER", "ROLE_PEOPLE_MANAGER", "ROLE_EMPLOYEE")),
    ASFLead(Sets.newHashSet("ROLE_ASF_LEAD", "ROLE_PORTFOLIO_MANAGER", "ROLE_PEOPLE_MANAGER", "ROLE_EMPLOYEE"));

    private Set<String> securityRoles;

    Role(Set<String> securityRoles) {
        this.securityRoles = securityRoles;
    }

    public Set<String> getSecurityRoles() {
        return securityRoles;
    }
}
