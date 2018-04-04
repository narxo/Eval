package com.cegeka.evaluations.domain.agreement;

public class AgreementDate {
    private int year;
    private int month;

    public AgreementDate(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
