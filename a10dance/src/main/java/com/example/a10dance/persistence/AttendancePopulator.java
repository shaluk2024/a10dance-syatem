package com.example.a10dance.persistence;

import com.example.a10dance.domain.Attendance;
import com.example.a10dance.domain.MonthYearData;
import com.example.a10dance.persistence.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AttendancePopulator implements CommandLineRunner {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MonthYearDataRepository monthYearDataRepository;

    @Override
    public void run(String... args) throws Exception {

        populateMonthYearTable();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        final var attendances = Arrays.asList(new Attendance(sdf.parse("2024-01-05 09:30:00"), sdf.parse("2024-01-05 17:30:00"), 8, 2024, 1)
                , new Attendance(sdf.parse("2024-01-06 09:30:00"), sdf.parse("2024-01-06 17:30:00"), 8, 2024, 1)
                , new Attendance(sdf.parse("2024-02-05 10:30:00"), sdf.parse("2024-02-05 17:30:00"), 7,  2024,  2)
                , new Attendance(sdf.parse("2024-02-06 09:30:00"), sdf.parse("2024-02-06 17:30:00"), 8,  2024, 2)
                , new Attendance(sdf.parse("2024-03-07 10:30:00"), sdf.parse("2024-03-07 17:30:00"), 7, 2024, 3));
                //, new Attendance(sdf.parse("2024-04-08 11:30:00"), sdf.parse("2024-04-08 17:30:00"), 6, 2024, 4)
                //, new Attendance(sdf.parse("2024-04-09 09:00:00"), sdf.parse("2024-04-09 17:00:00"), 8, 2024, 4));

        attendanceRepository.saveAll(attendances);
    }

    public void populateMonthYearTable() {
        List<MonthYearData> monthYearList = new ArrayList<>();

        int startYear = 2001;
        int endYear = 3000;

        for (int year = startYear; year <= endYear; year++) {
            for (int month = 1; month <= 12; month++) {
                MonthYearData monthYear = new MonthYearData(month, year);

                monthYearList.add(monthYear);
            }
        }

        monthYearDataRepository.saveAll(monthYearList);
    }
}
