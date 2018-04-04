package com.cegeka.evaluations.domain.stampcard;

import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;

import static com.cegeka.evaluations.domain.stampcard.StampCardInputDTOType.*;

public enum StampCardInputDTOPeriod {
    JAN(1, AGREEMENT),
    FEB(2, AGREEMENT),
    MAR(3, AGREEMENT),
    APR(4, AGREEMENT),
    MAY(5, AGREEMENT),
    JUN(6, AGREEMENT),
    JUL(7, AGREEMENT),
    AUG(8, AGREEMENT),
    SEP(9, AGREEMENT),
    OCT(10, AGREEMENT),
    NOV(11, AGREEMENT),
    DEC(12, AGREEMENT),

    Q1(1, QSCORE),
    Q2(2, QSCORE),
    Q3(3, QSCORE),
    Q4(4, QSCORE);

    public int getNumber() {
        return number;
    }

    private int number;
    private StampCardInputDTOType stampCardInputDTOType;

    StampCardInputDTOPeriod(int number, StampCardInputDTOType stampCardInputDTOType) {
        this.number = number;
        this.stampCardInputDTOType = stampCardInputDTOType;
    }

    public static StampCardInputDTOPeriod getPeriodByMonth(int number) {
        return Arrays.asList(StampCardInputDTOPeriod.values())
                .stream()
                .filter(enumval -> enumval.stampCardInputDTOType == AGREEMENT)
                .filter(enumVal -> enumVal.number == number)
                .findFirst()
                .orElse(null);
    }

    public static StampCardInputDTOPeriod getPeriodByQuadrimester(int number) {
        return Arrays.asList(StampCardInputDTOPeriod.values())
                .stream()
                .filter(enumval -> enumval.stampCardInputDTOType == QSCORE)
                .filter(enumVal -> enumVal.number == number)
                .findFirst()
                .orElse(null);
    }

    public static int getMonthNumberByPeriod(String month) {
        return Objects.requireNonNull(Arrays.asList(StampCardInputDTOPeriod.values())
                .stream()
                .filter(enumval -> enumval.stampCardInputDTOType == AGREEMENT)
                .filter(enumVal -> enumVal.name().equals(month))
                .findFirst().orElse(null)).number;
    }

    public static int getQuadrimesterNumberByPeriod(String quadrimester) {
        return Objects.requireNonNull(Arrays.asList(StampCardInputDTOPeriod.values())
                .stream()
                .filter(enumval -> enumval.stampCardInputDTOType == QSCORE)
                .filter(enumVal -> enumVal.name().equals(quadrimester))
                .findFirst().orElse(null)).number;
    }
}
