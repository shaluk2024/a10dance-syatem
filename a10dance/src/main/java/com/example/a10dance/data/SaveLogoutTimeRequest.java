package com.example.a10dance.data;

import java.util.Date;

public class SaveLogoutTimeRequest {

    private Date loginTime;

    private Date logoutTime;

    SaveLogoutTimeRequest() {
        super();
    }

    public SaveLogoutTimeRequest(final Date loginTime, final  Date logoutTime) {
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }


    public Date getLogoutTime() {
        return logoutTime;
    }

}
