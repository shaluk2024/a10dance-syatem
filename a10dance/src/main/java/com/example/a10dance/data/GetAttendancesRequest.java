package com.example.a10dance.data;

import java.io.Serial;
import java.util.Date;

import com.example.a10dance.common.PaginatedRequest;

public class GetAttendancesRequest extends PaginatedRequest{
    @Serial
    private static final long serialVersionUID = 1L;

    private Date loginDate;

    private Date loginTime;

    private  Date logoutTime;

    GetAttendancesRequest() {
        super();
    }

    public GetAttendancesRequest(final Date loginDate, final Date loginTime, final Date logoutTime) {
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
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
}
