package com.cegeka.evaluations.domain.populateDB;

import java.util.List;

public interface LdapQuerier {
    List<LdapEmployeeDTO> getAllEmployeesFromLdap();
}
