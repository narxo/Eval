package com.cegeka.evaluations.domain.agreement;

import com.cegeka.evaluations.domain.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "AGREEMENT")
public class Agreement {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "EVALUATORCONFIRMATION")
    private Boolean evaluatorConfirmation;

    @Column(name = "EVALUEECONFIRMATION")
    private Boolean evalueeConfirmation;

    public Agreement() {
    }

    public Agreement(Employee employee, Employee manager, Integer year, Integer month, Boolean evaluatorConfirmation, Boolean evalueeConfirmation) {
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.month = month;
        this.evaluatorConfirmation = evaluatorConfirmation;
        this.evalueeConfirmation = evalueeConfirmation;
    }

    public Agreement(Integer id, Employee employee, Employee manager, Integer year, Integer month, Boolean evaluatorConfirmation, Boolean evalueeConfirmation) {
        this.id = id;
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.month = month;
        this.evaluatorConfirmation = evaluatorConfirmation;
        this.evalueeConfirmation = evalueeConfirmation;
    }

    public Agreement(Employee employee, Integer year, Integer month, Boolean evaluatorConfirmation, Boolean evalueeConfirmation) {
        this.employee = employee;
        this.year = year;
        this.month = month;
        this.evaluatorConfirmation = evaluatorConfirmation;
        this.evalueeConfirmation = evalueeConfirmation;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getEvalueeConfirmation() {
        return evalueeConfirmation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Employee getManager() {
        return manager;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Boolean getEvaluatorConfirmation() {
        return evaluatorConfirmation;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setEvaluatorConfirmation(Boolean evaluatorConfirmation) {
        this.evaluatorConfirmation = evaluatorConfirmation;
    }

    public void setEvalueeConfirmation(Boolean evalueeConfirmation) {
        this.evalueeConfirmation = evalueeConfirmation;
    }

}
