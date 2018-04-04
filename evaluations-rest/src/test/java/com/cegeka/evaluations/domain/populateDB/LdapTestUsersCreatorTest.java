package com.cegeka.evaluations.domain.populateDB;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LdapTestUsersCreatorTest {

//    @InjectMocks
//    private LdapTestUsersCreator ldapTestUsersCreator;
//
//    private LdapEmployeeDTO employee1,employee2,asfLead,portfolioManager,peopleManager;
//
//    private ArrayList<LdapEmployeeDTO> testList;
//
//    @Before
//    public void setUp() throws Exception {
//        testList = new ArrayList<>();
//
//        employee1 = new LdapEmployeeDTO();
//        employee2 = new LdapEmployeeDTO();
//        asfLead = new LdapEmployeeDTO();
//        peopleManager = new LdapEmployeeDTO();
//        portfolioManager = new LdapEmployeeDTO();
//
//        employee1.setCN("CN=TeamMember1");
//        employee1.setFirstName("Team");
//        employee1.setLastName("Member1");
//        employee1.setUserName("TeamMember1".toLowerCase());
//        employee1.setManagerCN("CN=PeopleManager1");
//
//        employee2.setCN("CN=TeamMember2");
//        employee2.setFirstName("Team");
//        employee2.setLastName("Member2");
//        employee2.setUserName("TeamMember2".toLowerCase());
//        employee2.setManagerCN("CN=PeopleManager1");
//
//        asfLead.setCN("CN=AsfLead");
//        asfLead.setFirstName("Asf");
//        asfLead.setLastName("Lead");
//        asfLead.setUserName("AsfLead".toLowerCase());
//        asfLead.setManagerCN("ManagerDummy");
//
//        peopleManager.setCN("CN=PeopleManager1");
//        peopleManager.setFirstName("People");
//        peopleManager.setLastName("Manager1");
//        peopleManager.setUserName("PeopleManager1".toLowerCase());
//        peopleManager.setManagerCN("CN=PortfolioManager1");
//
//        portfolioManager.setCN("CN=PortfolioManager1");
//        portfolioManager.setFirstName("Portfolio");
//        portfolioManager.setLastName("Manager1");
//        portfolioManager.setUserName("PortfolioManager1".toLowerCase());
//        portfolioManager.setManagerCN("CN=AsfLead");
//        portfolioManager.setIsPortfolioManager(true);
//
//        testList.add(employee1);
//        testList.add(employee2);
//        testList.add(asfLead);
//        testList.add(peopleManager);
//        testList.add(portfolioManager);
//    }
//
//    @Test
//    public void getAllEmployeesFromLdap_shouldContainMultipleEmployeeTypes() throws Exception {
//        when(ldapTestUsersCreator.getAllEmployeesFromLdap()).thenReturn(testList);
//        assertThat(ldapTestUsersCreator.getAllEmployeesFromLdap()).containsExactly(employee1,employee2,asfLead,peopleManager,portfolioManager);
//
//    }
}