package com.cegeka.evaluations.domain.qscores;

public class UpdateQscoreDTO {
    public String evaluator;
    public String evaluee;
    public String quadrimester;
    public int qScore;
    public String comment;

    public UpdateQscoreDTO() {
    }

    public UpdateQscoreDTO(String evaluator, String evaluee, String quadrimester, int qScore, String comment) {
        this.evaluator = evaluator;
        this.evaluee = evaluee;
        this.quadrimester = quadrimester;
        this.qScore = qScore;
        this.comment = comment;
    }
}
