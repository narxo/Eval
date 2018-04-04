package com.cegeka.evaluations.domain.agreement;

public class AgreementDTO {

    private int id;
    private String evaluator;
    private String evaluee;
    private boolean evaluatorConfirmation;
    private boolean evalueeConfirmation;
    private AgreementDate date;

    public AgreementDTO() {
    }

    public AgreementDTO(int id, String evaluator, String evaluee, boolean evaluatorConfirmation, boolean evalueeConfirmation, AgreementDate date) {
        this.id = id;
        this.evaluator = evaluator;
        this.evaluee = evaluee;
        this.evaluatorConfirmation = evaluatorConfirmation;
        this.evalueeConfirmation = evalueeConfirmation;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public String getEvaluee() {
        return evaluee;
    }

    public boolean isEvaluatorConfirmation() {
        return evaluatorConfirmation;
    }

    public boolean isEvalueeConfirmation() {
        return evalueeConfirmation;
    }

    public AgreementDate getDate() {
        return date;
    }

}
