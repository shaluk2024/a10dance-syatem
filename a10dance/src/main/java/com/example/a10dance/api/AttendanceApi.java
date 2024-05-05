package com.example.a10dance.api;

import com.example.a10dance.data.*;
import com.example.a10dance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/attendances")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceApi {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/")
    public ResponseEntity<GetAttendancesResponse> getAttendances(final Date loginDate
            , final Date loginTime
            , final Date logoutTime
            , @RequestParam(defaultValue = "0") final int pageNumber
            , @RequestParam(defaultValue = "10") final int pageSize) {
               GetAttendancesRequest request =  new GetAttendancesRequest(loginDate
                , loginTime
                , logoutTime);
            
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);    
                
        return ResponseEntity.ok(attendanceService.getAttendances(request));
    }

    @GetMapping("/summary")
    public ResponseEntity<GetAttendanceSummaryResponse> getAttendancesSummary(final Date loginTime
            , final Date logoutTime) {
        return ResponseEntity.ok(attendanceService.getAttendances(new GetAttendanceSummaryRequest(loginTime
                , logoutTime)));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/save/login")
    public ResponseEntity<SaveLoginTimeResponse> saveLogin(@RequestBody final SaveLoginTimeRequest request){
        final  var response = attendanceService.saveLoginTime(request);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/save/logout")
    public ResponseEntity<SaveLogoutTimeResponse> saveLogout(@RequestBody final SaveLogoutTimeRequest request){
        final  var response = attendanceService.saveLogoutTime(request);

        return ResponseEntity.ok(response);
    }

}
