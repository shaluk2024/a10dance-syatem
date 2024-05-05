package com.example.a10dance.data;

import java.util.Date;

public class GetAttendanceSummaryRequest {

    private Date loginTime;

    private Date logoutTime;

    public GetAttendanceSummaryRequest() {
        super();
    }

    public GetAttendanceSummaryRequest(final Date loginTime, final Date logoutTime) {
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
