package com.example.a10dance.data;

import java.util.ArrayList;
import java.util.Collection;

import com.example.a10dance.common.PaginatedResponse;

public class GetAttendancesResponse extends PaginatedResponse{

    private Collection<AttendanceDetails> attendances;

    public void addAttendance(final AttendanceDetails attendance) {
        if (attendances == null) {
            attendances = new ArrayList<>();
        }

        attendances.add(attendance);
    }

    public Collection<AttendanceDetails> getAttendances() {
        return attendances;
    }
}
