package com.example.a10dance.data;

import com.example.a10dance.common.Response;

import java.util.ArrayList;
import java.util.Collection;

public class GetAttendanceSummaryResponse extends Response {

    private Collection<AttendanceSummary> attendances;

    public void addAttendance(final AttendanceSummary attendance) {
        if (attendances == null) {
            attendances = new ArrayList<>();
        }

        attendances.add(attendance);
    }

    public Collection<AttendanceSummary> getAttendances() {
        return attendances;
    }
}
