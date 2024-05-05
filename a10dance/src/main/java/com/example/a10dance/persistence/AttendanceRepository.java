package com.example.a10dance.persistence;

import com.example.a10dance.data.MonthlyAttendanceSummary;
import com.example.a10dance.domain.Attendance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    /**
     * Default sorting configuration for queries, ordering by date in descending order.
     */
    Sort DEFAULT_SORT = Sort.by(Sort.Direction.DESC, "loginTime");

    /**
     * Retrieves a paginated list of all attendance records based on the specified page number and page size.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of records per page.
     * @return A Page object containing the requested attendance records.
     */
    default Page<Attendance> findAll(final int pageNumber, final int pageSize) {
        return findAll(PageRequest.of(pageNumber, pageSize, DEFAULT_SORT));
    }

    /**
     * Retrieves a list of all attendance records, sorted by default.
     *
     * @return A list containing all attendance records.
     */
    default List<Attendance> findAll() {
        return findAll(DEFAULT_SORT);
    }

    /**
     * Retrieves a list of attendance records for a specific date.
     *
     * date The date for which attendance records are requested.
     * @return A list of attendance records for the specified date.
     */
    List<Attendance> findByLoginTime(Date date);

    @Query(
            value= "SELECT "+" new com.example.a10dance.data.MonthlyAttendanceSummary(a.monthValue as month , a.yearValue as year, SUM(a.workingHours) as workingHours,  "+
            "CONCAT(MONTHNAME(a.loginTime), ', ', a.yearValue) as monthYear )" +
                    "FROM Attendance a "+
                    "INNER JOIN "+
                    "MonthYearData m "+
                    "ON "+
                    "a.monthValue=m.monthValue "+
                    "AND "+
                    "a.yearValue=m.yearValue " +
                    "GROUP BY month, year, monthYear " +
                    "ORDER BY year DESC, month DESC"
    )
    List<MonthlyAttendanceSummary> findMonthlyAttendance();
}
