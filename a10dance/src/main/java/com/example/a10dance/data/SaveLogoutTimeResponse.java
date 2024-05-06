package com.example.a10dance.data;


import com.example.a10dance.common.Response;

import java.util.Date;

public class SaveLogoutTimeResponse extends Response {

    private Date loginTime;

    private Date logoutTime;

    private long workingHours;


    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(final Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }


    public void setLogoutTime(final Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public long getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(long workingHours) {
        this.workingHours = workingHours;
    }
}
