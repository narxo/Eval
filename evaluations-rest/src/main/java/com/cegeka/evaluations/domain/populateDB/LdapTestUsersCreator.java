package com.cegeka.evaluations.domain.populateDB;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("!prd")
public class LdapTestUsersCreator implements LdapQuerier {

    @Override
    public List<LdapEmployeeDTO> getAllEmployeesFromLdap() {
        return createMinimalHierarchy();
    }


    private List<LdapEmployeeDTO> createMinimalHierarchy() {
        ArrayList<LdapEmployeeDTO> minimalHierarchy = new ArrayList<>();
        minimalHierarchy.add(createAsfLead());
        minimalHierarchy.add(createPortfolioManager1());
        minimalHierarchy.add(createPortfolioManager2());
        minimalHierarchy.add(createPortfolioManager3());
        minimalHierarchy.add(createPeopleManager1());
        minimalHierarchy.add(createPeopleManager2());
        minimalHierarchy.add(createPeopleManager3());
        minimalHierarchy.add(createPeopleManager4());
        minimalHierarchy.add(createPeopleManager5());
        minimalHierarchy.add(createPeopleManager6());
        minimalHierarchy.add(createEmployee1());
        minimalHierarchy.add(createEmployee2());
        minimalHierarchy.add(createEmployee3());
        minimalHierarchy.add(createEmployee4());
        minimalHierarchy.add(createEmployee5());
        minimalHierarchy.add(createEmployee6());
        minimalHierarchy.add(createEmployee7());
        minimalHierarchy.add(createEmployee8());
        minimalHierarchy.add(createEmployee9());
        minimalHierarchy.add(createEmployee10());
        minimalHierarchy.add(createEmployee11());
        minimalHierarchy.add(createEmployee12());
        minimalHierarchy.add(createEmployee13());
        minimalHierarchy.add(createEmployee14());
        minimalHierarchy.add(createEmployee15());
        minimalHierarchy.add(createEmployee16());
        minimalHierarchy.add(createEmployee17());
        minimalHierarchy.add(createEmployee18());
        minimalHierarchy.add(createEmployee19());
        minimalHierarchy.add(createEmployee20());


        return minimalHierarchy;
    }

    private LdapEmployeeDTO createEmployee1() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember1");
        employee.setFirstName("Team");
        employee.setLastName("Member1");
        employee.setUserName("TeamMember1".toLowerCase());
        employee.setManagerCN("CN=PeopleManager1");
        return employee;
    }

    private LdapEmployeeDTO createEmployee2() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember2");
        employee.setFirstName("Team");
        employee.setLastName("Member2");
        employee.setUserName("TeamMember2".toLowerCase());
        employee.setManagerCN("CN=PeopleManager1");
        return employee;
    }

    private LdapEmployeeDTO createEmployee3() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember3");
        employee.setFirstName("Team");
        employee.setLastName("Member3");
        employee.setUserName("TeamMember3".toLowerCase());
        employee.setManagerCN("CN=PeopleManager2");
        return employee;
    }

    private LdapEmployeeDTO createEmployee4() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember4");
        employee.setFirstName("Team");
        employee.setLastName("Member4");
        employee.setUserName("TeamMember4".toLowerCase());
        employee.setManagerCN("CN=PeopleManager2");
        return employee;
    }

    private LdapEmployeeDTO createEmployee5() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember5");
        employee.setFirstName("Team");
        employee.setLastName("Member5");
        employee.setUserName("TeamMember5".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager3");
        return employee;
    }

    private LdapEmployeeDTO createEmployee6() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember6");
        employee.setFirstName("Team");
        employee.setLastName("Member6");
        employee.setUserName("TeamMember6".toLowerCase());
        employee.setManagerCN("CN=PeopleManager3");
        return employee;
    }

    private LdapEmployeeDTO createEmployee7() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember7");
        employee.setFirstName("Team");
        employee.setLastName("Member7");
        employee.setUserName("TeamMember7".toLowerCase());
        employee.setManagerCN("CN=PeopleManager3");
        return employee;
    }

    private LdapEmployeeDTO createEmployee8() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember8");
        employee.setFirstName("Team");
        employee.setLastName("Member8");
        employee.setUserName("TeamMember8".toLowerCase());
        employee.setManagerCN("CN=PeopleManager4");
        return employee;
    }

    private LdapEmployeeDTO createEmployee9() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember9");
        employee.setFirstName("Team");
        employee.setLastName("Member9");
        employee.setUserName("TeamMember9".toLowerCase().toLowerCase());
        employee.setManagerCN("CN=PeopleManager5");
        return employee;
    }

    private LdapEmployeeDTO createEmployee10() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember10");
        employee.setFirstName("Team");
        employee.setLastName("Member10");
        employee.setUserName("TeamMember10".toLowerCase());
        employee.setManagerCN("CN=PeopleManager5");
        return employee;
    }

    private LdapEmployeeDTO createEmployee11() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember11");
        employee.setFirstName("Team");
        employee.setLastName("Member11");
        employee.setUserName("TeamMember11".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee12() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember12");
        employee.setFirstName("Team");
        employee.setLastName("Member12");
        employee.setUserName("TeamMember12".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee13() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember13");
        employee.setFirstName("Team");
        employee.setLastName("Member13");
        employee.setUserName("TeamMember13".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee14() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember14");
        employee.setFirstName("Team");
        employee.setLastName("Member14");
        employee.setUserName("TeamMember14".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee15() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember15");
        employee.setFirstName("Team");
        employee.setLastName("Member15");
        employee.setUserName("TeamMember15".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee16() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember16");
        employee.setFirstName("Team");
        employee.setLastName("Member16");
        employee.setUserName("TeamMember16".toLowerCase());
        employee.setManagerCN("CN=PeopleManager6");
        return employee;
    }

    private LdapEmployeeDTO createEmployee17() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember17");
        employee.setFirstName("Team");
        employee.setLastName("Member17");
        employee.setUserName("TeamMember17".toLowerCase());
        employee.setManagerCN("CN=AsfLead");
        return employee;
    }

    private LdapEmployeeDTO createEmployee18() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember18");
        employee.setFirstName("Team");
        employee.setLastName("Member18");
        employee.setUserName("TeamMember18".toLowerCase());
        employee.setManagerCN("CN=AsfLead");
        return employee;
    }

    private LdapEmployeeDTO createEmployee19() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember19");
        employee.setFirstName("Team");
        employee.setLastName("Member19");
        employee.setUserName("TeamMember19".toLowerCase());
        employee.setManagerCN("CN=PeopleManager7");
        return employee;
    }

    private LdapEmployeeDTO createEmployee20() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=TeamMember20");
        employee.setFirstName("Team");
        employee.setLastName("Member20");
        employee.setUserName("TeamMember20".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager3");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager1() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager1");
        employee.setFirstName("People");
        employee.setLastName("Manager1");
        employee.setUserName("PeopleManager1".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager1");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager2() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager2");
        employee.setFirstName("People");
        employee.setLastName("Manager2");
        employee.setUserName("PeopleManager2".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager1");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager3() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager3");
        employee.setFirstName("People");
        employee.setLastName("Manager3");
        employee.setUserName("PeopleManager3".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager2");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager4() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager4");
        employee.setFirstName("People");
        employee.setLastName("Manager4");
        employee.setUserName("PeopleManager4".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager2");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager5() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager5");
        employee.setFirstName("People");
        employee.setLastName("Manager5");
        employee.setUserName("PeopleManager5".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager2");
        return employee;
    }

    private LdapEmployeeDTO createPeopleManager6() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PeopleManager6");
        employee.setFirstName("People");
        employee.setLastName("Manager6");
        employee.setUserName("PeopleManager6".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager3");
        return employee;
    }

    private LdapEmployeeDTO createPortfolioManager1() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PortfolioManager1");
        employee.setFirstName("Portfolio");
        employee.setLastName("Manager1");
        employee.setUserName("PortfolioManager1".toLowerCase());
        employee.setManagerCN("CN=AsfLead");
        employee.setIsPortfolioManager(true);
        return employee;
    }

    private LdapEmployeeDTO createPortfolioManager2() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PortfolioManager2");
        employee.setFirstName("Portfolio");
        employee.setLastName("Manager2");
        employee.setUserName("PortfolioManager2".toLowerCase());
        employee.setManagerCN("CN=AsfLead");
        employee.setIsPortfolioManager(true);
        return employee;
    }

    private LdapEmployeeDTO createPortfolioManager3() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=PortfolioManager3");
        employee.setFirstName("Portfolio");
        employee.setLastName("Manager3");
        employee.setUserName("PortfolioManager3".toLowerCase());
        employee.setManagerCN("CN=PortfolioManager1");
        employee.setIsPortfolioManager(true);
        return employee;
    }

    private LdapEmployeeDTO createAsfLead() {
        LdapEmployeeDTO employee = new LdapEmployeeDTO();
        employee.setCN("CN=AsfLead");
        employee.setFirstName("Asf");
        employee.setLastName("Lead");
        employee.setUserName("AsfLead".toLowerCase());
        employee.setManagerCN("ManagerDummy");
        return employee;
    }
}
