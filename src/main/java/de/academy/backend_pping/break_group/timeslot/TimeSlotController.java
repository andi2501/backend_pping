package de.academy.backend_pping.break_group.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/break/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/")
    public List<TimeSlotDTO> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{foodId}")
    public List<TimeSlotDTO> getTimeSlotsForFoodOption(@PathVariable("foodId") Long foodId) {
        return timeSlotService.getTimeSlotsForFoodOption(foodId);
    }
}
