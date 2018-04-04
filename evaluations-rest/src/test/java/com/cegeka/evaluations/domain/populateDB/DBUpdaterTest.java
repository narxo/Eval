package com.cegeka.evaluations.domain.populateDB;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


public class DBUpdaterTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private DBUpdater dbUpdater;

    @Mock
    private LdapQuerier ldapQuerier;

    @Mock
    private DBpopulator dBpopulator;

    private List<LdapEmployeeDTO> nenLdapList;
    private LdapEmployeeDTO ldapEmployeeDTO;

    @Before
    public void setUp() throws Exception {
        ldapEmployeeDTO = new LdapEmployeeDTO();
        nenLdapList = new ArrayList();
        nenLdapList.add(ldapEmployeeDTO);


    }

    @Test
    public void updateAll_whenEmployeesFromLDAPIsNotNull_shouldInvokePopulateDBsPopulateDB() {
        when(ldapQuerier.getAllEmployeesFromLdap()).thenReturn(nenLdapList);
        dbUpdater.updateAll();
        verify(dBpopulator).populateDB(nenLdapList);
    }

    @Test
    public void updateAll_whenEmployeesFromLDAPIsNull_shouldThrowRuntimeException() {
        thrown.expect(RuntimeException.class);
        when(ldapQuerier.getAllEmployeesFromLdap()).thenReturn(null);
        dbUpdater.updateAll();
    }
}