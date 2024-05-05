package com.example.a10dance.service;

import com.example.a10dance.data.*;
import com.example.a10dance.domain.Attendance;
import com.example.a10dance.persistence.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public GetAttendancesResponse getAttendances(final GetAttendancesRequest request) {
        final var response = new GetAttendancesResponse();
        final var attendances = attendanceRepository.findAll(request.getPageNumber(), request.getPageSize());

        response.setPageNumber(request.getPageNumber());
        response.setPageSize(request.getPageSize());
        response.setTotal(attendances.getTotalElements());

        for (final var attendance : attendances) {

            response.addAttendance(new AttendanceDetails(attendance.getLoginTime()
                    , attendance.getLoginTime()
                    , attendance.getLogoutTime()
                    , attendance.getWorkingHours()));
        }

        return response;
    }

    public GetAttendanceSummaryResponse getAttendances(final GetAttendanceSummaryRequest request) {
        final var response = new GetAttendanceSummaryResponse();

        for (final var attendance : attendanceRepository.findMonthlyAttendance()) {

            response.addAttendance(new AttendanceSummary(attendance.getMonthYear()
                    , attendance.getWorkingHours()));
        }

        return response;
    }

    public SaveLoginTimeResponse saveLoginTime(final SaveLoginTimeRequest request) {
        List<Attendance> attendances = attendanceRepository.findByLoginTime(request.getLoginTime());

        System.out.println(request.getLoginTime());

        final var response = new SaveLoginTimeResponse();


        if (attendances.isEmpty()) {
            Attendance attendance = new Attendance(request.getLoginTime(), getAutoLogoutTime(request.getLoginTime()), 0, getYear(request.getLoginTime()), getMonth(request.getLoginTime()));
            Attendance savedAttendance = attendanceRepository.save(attendance);

            System.out.println(savedAttendance.getLogoutTime());

            response.setLoginTime(savedAttendance.getLoginTime());
            response.setLogoutTime(savedAttendance.getLogoutTime());

            response.setMessage("Login successfully");
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Already logged in.");
        }

        return response;

    }

    public SaveLogoutTimeResponse saveLogoutTime(final SaveLogoutTimeRequest request) {
        List<Attendance> attendances = attendanceRepository.findByLoginTime(request.getLoginTime());
        final var response = new SaveLogoutTimeResponse();


        if (!attendances.isEmpty()) {
            final Attendance attendance = attendances.get(0);

            System.out.println(attendance.getLogoutTime());
            System.out.println(attendance.getLoginTime());
            System.out.println(request.getLogoutTime());



            if (request.getLogoutTime() != null) {
                attendance.setLogoutTime(request.getLogoutTime());

                final long duration = calculateDuration(attendance.getLoginTime(), attendance.getLogoutTime());
                attendance.setWorkingHours(duration);
                attendanceRepository.save(attendance);

                response.setLoginTime(attendance.getLoginTime());
                response.setLogoutTime(attendance.getLogoutTime());
                response.setWorkingHours(duration);

                response.setMessage("Logout successfully");
                response.setStatus(HttpStatus.OK.value());
            } else {
                response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
                response.setError("Logout time required.");
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Already logged out.");
        }

        return response;

    }

    long calculateDuration(final Date loginTime, final Date logoutTime) {
        long durationMillis;

        durationMillis = logoutTime.getTime() - loginTime.getTime();

        // Convert milliseconds to hours
        long durationHours = durationMillis / (60 * 60 * 1000);

        return  durationHours;
    }

    Date getAutoLogoutTime(Date loginDate) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(loginDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date logoutTime = calendar.getTime();

        System.out.println(logoutTime);

        return Date.from(logoutTime.toInstant());
    }

    Integer getYear(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        return year;

    }

    Integer getMonth(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH);
        return month;

    }


}
