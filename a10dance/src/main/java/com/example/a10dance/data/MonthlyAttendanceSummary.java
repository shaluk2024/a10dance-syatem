package com.example.a10dance.data;

public class MonthlyAttendanceSummary {

    private int month;
    private String monthYear;

    private long workingHours;

    private  int year;

    MonthlyAttendanceSummary() {
        super();
    }

    public MonthlyAttendanceSummary(final int month, final int year, final long workingHours, final String monthYear) {
        this.month = month;
        this.year = year;
        this.monthYear = monthYear;
        this.workingHours = workingHours;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public long getWorkingHours() {
        return workingHours;
    }


    public int getYear() {
        return year;
    }
}
