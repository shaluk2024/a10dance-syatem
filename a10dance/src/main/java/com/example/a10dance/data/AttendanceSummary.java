package com.example.a10dance.data;

public class AttendanceSummary {

    private String monthYear;

    private long workingHours;

    AttendanceSummary() {
        super();
    }

    public AttendanceSummary(final String monthYear, final long workingHours) {
        this.monthYear = monthYear;
        this.workingHours = workingHours;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public long getWorkingHours() {
        return workingHours;
    }
}
