package com.cegeka.evaluations.infrastructure.clock;

import java.time.Clock;
import java.time.LocalDate;

public class TimeManager {
    
    private static Clock clock;

    static {
        setClockToCurrentDateTime();
    }

    public static void setClockToCurrentDateTime() {
        setClock(Clock.system(Clock.systemDefaultZone().getZone()));
    }

    public static void setClock(Clock clock) {
        TimeManager.clock = clock;
    }

    public static Clock getClock() {
        return clock;
    }

    public static LocalDate getNow() {
        return LocalDate.now(clock);
    }

    public static int getCurrentYear() {
        return getNow().getYear();
    }
}
