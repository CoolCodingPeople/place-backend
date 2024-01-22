package com.nighthawk.spring_portfolio.mvc.schedule;

import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleApiController {

    private ScheduleData scheduleData; // Assuming you store the schedule data in memory

    @PostMapping("/api/schedule")
    public String updateSchedule(@RequestBody ScheduleData scheduleData) {
        // Process and organize the schedule data as per your requirements
        // You can store it in a database, perform calculations, or any other desired actions
        this.scheduleData = scheduleData; // Update the schedule data
        
        // Example response message
        return "Schedule updated successfully";
    }

    @GetMapping("/api/schedule")
    public ScheduleData getSchedule() {
        // Return the schedule data to the frontend
        return scheduleData;
    }
}