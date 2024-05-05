package com.example.a10dance.domain;

import java.io.Serial;
import java.util.Date;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "login_time", nullable = false, updatable = false)
    @NotNull
    private  Date loginTime;
    
    @Column(name = "logout_time", nullable = true, updatable = true)
    private  Date logoutTime;

    @Column(name = "month_value", nullable = false, updatable = false)
    @NotNull
    private Integer monthValue;

    @Column(name = "working_hours", nullable = true, updatable = true)
    private long workingHours;

    @Column(name = "year_value", nullable = false, updatable = false)
    @NotNull
    private Integer yearValue;

    

    Attendance() {
        super();
    }

    public Attendance(final Date loginTime, final Date logoutTime, final long workingHours, final Integer yearValue, final Integer monthValue) {
        this();
        setLoginTime(loginTime);
        setLogoutTime(logoutTime);
        setWorkingHours(workingHours);
        setYearValue(yearValue);
        setMonthValue(monthValue);
    }

    public Long getId() {
        return id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime){
        if(loginTime == null){
            throw new NullPointerException("Login time is mandatory.");
        }

        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime){
        if(logoutTime == null){
            throw new NullPointerException("Logout time is mandatory.");
        }

        this.logoutTime = logoutTime;
    }

    public long getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(long workingHours) {
        this.workingHours = workingHours;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        if(monthValue == null){
            throw new NullPointerException("month is mandatory.");
        }
        this.monthValue = monthValue;
    }

    public Integer getYearValue() {
        return yearValue;
    }

    public void setYearValue(Integer yearValue) {
        if(yearValue == null){
            throw new NullPointerException("year is mandatory.");
        }
        this.yearValue = yearValue;
    }
}
