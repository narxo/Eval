package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "QSCORES")
public class Qscore {
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

    @Column(name = "QUADRIMESTER")
    private Integer quadrimester;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "COMMENT")
    private String comment;

    public Qscore() {
    }

    public Qscore(Employee employee, Employee manager, Integer year, Integer quadrimester, int score) {
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.quadrimester = quadrimester;
        this.score = score;
    }

    public Qscore(Employee employee, Employee manager, Integer year, Integer quadrimester, int score, String comment) {
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.quadrimester = quadrimester;
        this.score = score;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
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

    public Integer getQuadrimester() {
        return quadrimester;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}