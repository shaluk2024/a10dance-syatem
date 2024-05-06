package com.example.a10dance.data;

import java.util.Date;

public class SaveLoginTimeRequest {
    private Date loginTime;

    SaveLoginTimeRequest() {
        super();
    }

    public SaveLoginTimeRequest(final Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
