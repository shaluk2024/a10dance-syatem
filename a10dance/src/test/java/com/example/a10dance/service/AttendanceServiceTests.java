package com.example.a10dance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.a10dance.data.*;
import com.example.a10dance.domain.Attendance;
import com.example.a10dance.persistence.AttendanceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.*;

public class AttendanceServiceTests {
    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAttendances() {
        // Mock data
        //AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        GetAttendancesRequest request = new GetAttendancesRequest(new Date(), new Date(), new Date());
        request.setPageNumber(1);
        request.setPageSize(10);

        Attendance attendance = new Attendance(new Date(), new Date(), 0, 2024, 5);
        List<Attendance> attendanceList = new ArrayList<>();
        attendanceList.add(attendance);
        // Mock the findAll method to return a Page of mock attendance records
        Page<Attendance> mockPage = new PageImpl<>(attendanceList);
        when(attendanceRepository.findAll(request.getPageNumber(), request.getPageSize())).thenReturn(mockPage);

        // Test
        GetAttendancesResponse response = attendanceService.getAttendances(request);

        // Verify
        assertEquals(request.getPageNumber(), response.getPageNumber());
        assertEquals(request.getPageSize(), response.getPageSize());
        assertEquals(1, response.getTotal());
        assertEquals(1, response.getAttendances().size());
    }

    @Test
    public void testGetAttendanceSummary() {
        // Mock data
        //AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        GetAttendanceSummaryRequest request = new GetAttendanceSummaryRequest();
        MonthlyAttendanceSummary monthlyAttendanceSummary = new MonthlyAttendanceSummary(5, 2024, 160, "May, 2024");

        AttendanceSummary attendanceSummary = new AttendanceSummary("May, 2024", 160);
        List<MonthlyAttendanceSummary> monthlyAttendanceSummaryList = new ArrayList<>();
        List<AttendanceSummary> attendanceSummaryList = new ArrayList<>();

        monthlyAttendanceSummaryList.add(monthlyAttendanceSummary);
        attendanceSummaryList.add(attendanceSummary);

        when(attendanceRepository.findMonthlyAttendance()).thenReturn(monthlyAttendanceSummaryList);

        // Test
        GetAttendanceSummaryResponse response = attendanceService.getAttendancesSummary(request);

        // Verify
        assertEquals(1, response.getAttendanceSummaries().size());
        assertEquals(attendanceSummary.getMonthYear(), response.getAttendanceSummaries().get(0).getMonthYear());
        assertEquals(attendanceSummary.getWorkingHours(), response.getAttendanceSummaries().get(0).getWorkingHours());
    }

    @Test
    public void testSaveLoginTimeSuccessful() {
        // Mock data
       // AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        Date loginTime = new Date();
        SaveLoginTimeRequest request = new SaveLoginTimeRequest(loginTime);
        when(attendanceRepository.findByLoginTime(loginTime)).thenReturn(new ArrayList<>());

        // Test
        SaveLoginTimeResponse response = attendanceService.saveLoginTime(request);

        // Verify
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Login successfully", response.getMessage());
    }

    @Test
    public void testSaveLoginTimeAlreadyLoggedIn() {
        // Mock data
        //AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        Date loginTime = new Date();
        SaveLoginTimeRequest request = new SaveLoginTimeRequest(loginTime);
        List<Attendance> attendances = new ArrayList<>();
        attendances.add(new Attendance(loginTime, new Date(), 0, 2024, 5));
        when(attendanceRepository.findByLoginTime(loginTime)).thenReturn(attendances);

        // Test
        SaveLoginTimeResponse response = attendanceService.saveLoginTime(request);

        // Verify
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Already logged in.", response.getError());
    }

    @Test
    public void testSaveLogoutTimeSuccessful() {
        // Mock data
        //AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        Date loginTime = new Date();
        Date logoutTime = new Date();
        SaveLogoutTimeRequest request = new SaveLogoutTimeRequest(loginTime, logoutTime);
        List<Attendance> attendances = new ArrayList<>();
        attendances.add(new Attendance(loginTime, logoutTime, 0, 2024, 5));
        when(attendanceRepository.findByLoginTime(loginTime)).thenReturn(attendances);

        // Test
        SaveLogoutTimeResponse response = attendanceService.saveLogoutTime(request);

        // Verify
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Logout successfully", response.getMessage());
        assertEquals(loginTime, response.getLoginTime());
        assertEquals(logoutTime, response.getLogoutTime());
    }

    @Test
    public void testSaveLogoutTimeWithoutLogin() {
        // Mock data
        //AttendanceRepository attendanceRepositoryMock = mock(AttendanceRepository.class);
        Date loginTime = new Date();
        Date logoutTime = new Date();
        SaveLogoutTimeRequest request = new SaveLogoutTimeRequest(loginTime, logoutTime);
        when(attendanceRepository.findByLoginTime(loginTime)).thenReturn(new ArrayList<>());

        // Test
        SaveLogoutTimeResponse response = attendanceService.saveLogoutTime(request);

        // Verify
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Already logged out.", response.getError());
    }

    @Test
    public void testCalculateDuration() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Test
        long duration = attendanceService.calculateDuration(sdf.parse("2024-05-05 09:00:00"), sdf.parse("2024-05-05 17:00:00"));

        // Verify
        assertEquals(8, duration);
    }

    @Test
    public void testGetAutoLogoutTime() {
        // Mock data
        Date loginTime = new Date();

        // Test
        Date autoLogoutTime = attendanceService.getAutoLogoutTime(loginTime);

        // Verify
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(autoLogoutTime);
        assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(59, calendar.get(Calendar.MINUTE));
        assertEquals(59, calendar.get(Calendar.SECOND));
    }

    @Test
    public void testGetYear() {
        // Mock data
        Date date = new Date(1641000000000L); // 2022-12-31

        // Test
        Integer year = attendanceService.getYear(date);

        // Verify
        assertEquals(2022, year.intValue());
    }

    @Test
    public void testGetMonth() {
        // Mock data
        Date date = new Date();

        // Test
        Integer month = attendanceService.getMonth(date);

        // Verify
        assertEquals(4, month);
    }

    // Add more test cases if necessary
}

