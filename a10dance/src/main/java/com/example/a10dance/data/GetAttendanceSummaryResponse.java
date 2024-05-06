package com.example.a10dance.data;

import com.example.a10dance.common.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetAttendanceSummaryResponse extends Response {

    private List<AttendanceSummary> attendanceSummaries;

    public void addAttendance(final AttendanceSummary attendance) {
        if (attendanceSummaries == null) {
            attendanceSummaries = new ArrayList<>();
        }

        attendanceSummaries.add(attendance);
    }

    public List<AttendanceSummary> getAttendanceSummaries() {
        return attendanceSummaries;
    }
}
