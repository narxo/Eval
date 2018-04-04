package com.cegeka.evaluations.domain.agreement;

public class ReverseConfirmationDTO {

    public String evaluator;
    public String evaluee;
    public String period;

    public ReverseConfirmationDTO() {
    }

    public ReverseConfirmationDTO(String evaluator, String evaluee, String period) {
        this.evaluator = evaluator;
        this.evaluee = evaluee;
        this.period = period;
    }
}
