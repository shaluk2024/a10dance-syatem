package com.example.a10dance.persistence;

import com.example.a10dance.domain.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendanceRepositoryTests {
    @Autowired
    private AttendanceRepository subject;

    @BeforeEach
    public void setUp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date loginTime;
        Date logoutTime;
        try {
            loginTime = sdf.parse("2024-04-30 09:00:00");
            logoutTime = sdf.parse("2024-04-30 17:00:00");

            subject.save(new Attendance(loginTime, logoutTime, 8, 2024, 4));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testDependenciesInjected(){
        assertNotNull(subject);
    }

    @Test
    void testFindByOrderByIdDesc() {

        final var attendances = subject.findAll();

        assertNotNull(attendances);

        assertTrue(attendances.iterator().hasNext());

        for (final var attendance : attendances) {
            assertNotNull(attendance);
            assertNotNull(attendance.getLoginTime());
            assertNotNull(attendance.getLogoutTime());
        }

    }
}