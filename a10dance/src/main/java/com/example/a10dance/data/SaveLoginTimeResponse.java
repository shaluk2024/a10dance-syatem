package com.example.a10dance.data;


import com.example.a10dance.common.Response;

import java.util.Date;

public class SaveLoginTimeResponse extends Response {

    private Date loginTime;

    private Date logoutTime;


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
}
