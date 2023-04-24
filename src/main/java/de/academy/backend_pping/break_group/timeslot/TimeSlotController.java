package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionService;
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

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping("/")
    public List<TimeSlotDTO> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{foodId}")
    public List<TimeSlotDTO> getTimeSlotsForFoodOption(@PathVariable("foodId") Long foodId) {
        return timeSlotService.getTimeSlotsForFoodOption(foodId);
    }

//    @PostMapping("/")
//    public @ResponseBody TimeSlotDTO addTimeSlotToFoodOption(@RequestBody FoodOptionDTO foodOptionDTO, @RequestBody TimeSlotDTO timeSlotDTO) {
//        return timeSlotService.addTimeSlotToFoodOption(timeSlotDTO, foodOptionService
//                .getFoodOptionByName(foodOptionDTO.getName()));
//    }
}
