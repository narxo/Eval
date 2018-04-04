package com.cegeka.evaluations.domain.populateDB;

import com.cegeka.evaluations.domain.roleservices.PeopleManagerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBUpdater {

    @Autowired
    private DBpopulator dBpopulator;

    @Autowired
    private PeopleManagerRoleService peopleManagerRoleService;

    @Autowired
    private LdapQuerier ldapQuerier;

    public void updateAll() {
        List<LdapEmployeeDTO> employeesFromLDAP = ldapQuerier.getAllEmployeesFromLdap();
        if (employeesFromLDAP == null) {
            throw new RuntimeException("Loading users from LDAP failed, aborting load users from LDAP...");
        }
        dBpopulator.populateDB(employeesFromLDAP);
    }
}
