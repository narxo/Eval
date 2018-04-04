package com.cegeka.evaluations.domain.populateDB;

public class LDAPEmployeeDTOTestBuilder {

    private String CN = "default CN";
    private String managerCN = "default managerCN";
    private Integer managerId = -1;
    private String firstName = "default firstName";
    private String lastName = "default lastName";
    private String userName = "default userName";
    private String portfolioManager = "default portfolioManager";

    public static LDAPEmployeeDTOTestBuilder anEmployee() {
        return new LDAPEmployeeDTOTestBuilder();
    }

    public LdapEmployeeDTO build() {
        LdapEmployeeDTO ldapEmployeeDTO = new LdapEmployeeDTO();
        ldapEmployeeDTO.setCN(CN);
        ldapEmployeeDTO.setManagerCN(managerCN);
        ldapEmployeeDTO.setManagerId(managerId);
        ldapEmployeeDTO.setFirstName(firstName);
        ldapEmployeeDTO.setLastName(lastName);
        ldapEmployeeDTO.setUserName(userName);
        boolean contains = portfolioManager.toLowerCase().contains("portfolio management");
        ldapEmployeeDTO.setIsPortfolioManager(contains);
        return ldapEmployeeDTO;
    }

    public LDAPEmployeeDTOTestBuilder withCN(String CN) {
        this.CN = CN;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withManagerCN(String managerCN) {
        this.managerCN = managerCN;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withManagerId(Integer managerId) {
        this.managerId = managerId;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public LDAPEmployeeDTOTestBuilder withPortfolioManager(String portfolioManager) {
        this.portfolioManager = portfolioManager;
        return this;
    }
}
