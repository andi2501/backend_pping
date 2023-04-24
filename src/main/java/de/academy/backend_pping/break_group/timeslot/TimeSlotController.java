package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionService;
import de.academy.backend_pping.buddy_core.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @PutMapping("/")
    public @ResponseBody TimeSlotDTO updateExistingTimeSlot(@RequestBody TimeSlotDTO oldTimeSlotDTO) {
        Random random = new Random();
        int randomNumber = random.nextInt(10000 + 1);
        String randNumber = Integer.toString(randomNumber);
        UserEntity currentUser = new UserEntity("user2323" + randNumber, "password23");
        TimeSlotDTO newTimeSlotDto = timeSlotService.update(currentUser, oldTimeSlotDTO);
        return newTimeSlotDto;
    }
}
