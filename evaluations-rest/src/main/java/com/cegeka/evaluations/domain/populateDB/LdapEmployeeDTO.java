package com.cegeka.evaluations.domain.populateDB;

public class LdapEmployeeDTO {
    private String CN;
    private String managerCN;
    private Integer managerId;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean isPortfolioManager = false;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerCN() {
        return managerCN;
    }

    public void setManagerCN(String managerCN) {
        this.managerCN = managerCN;
    }

    public void setIsPortfolioManager(boolean contains) {
        this.isPortfolioManager = contains;
    }

    public boolean isPortfolioManager() {
        return isPortfolioManager;
    }
}
