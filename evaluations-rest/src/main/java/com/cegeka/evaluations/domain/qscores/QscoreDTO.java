package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;

public class QscoreDTO {

    private int id;
    private Employee employee;
    private Employee manager;
    private Integer year;
    private Integer quadrimester;
    private int score;
    private String comment;


    public QscoreDTO() {
    }

    public QscoreDTO(int id, Employee employee, Employee manager, Integer year, Integer quadrimester, int score) {
        this.id = id;
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.quadrimester = quadrimester;
        this.score = score;
    }
    
    public QscoreDTO(int id, Employee employee, Employee manager, Integer year, Integer quadrimester, int score, String comment) {
        this.id = id;
        this.employee = employee;
        this.manager = manager;
        this.year = year;
        this.quadrimester = quadrimester;
        this.score = score;
        this.comment = comment;
    }

    public int getId() {
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

}
