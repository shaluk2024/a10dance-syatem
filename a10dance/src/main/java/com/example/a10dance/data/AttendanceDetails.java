package com.example.a10dance.data;

import java.io.Serial;
import java.util.Date;

public class AttendanceDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date loginDate;

    private Date loginTime;

    private Date logoutTime;

    private long workingHours;

    AttendanceDetails() {
        super();
    }

    public AttendanceDetails(final Date loginDate, final Date loginTime, final Date logoutTime, final long workingHours) {
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.workingHours = workingHours;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public long getWorkingHours() {
        return workingHours;
    }

    
}
