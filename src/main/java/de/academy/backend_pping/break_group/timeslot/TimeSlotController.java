package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionService;
import de.academy.backend_pping.buddy_core.session.SessionService;
import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/api/break/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private FoodOptionService foodOptionService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<TimeSlotDTO> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{foodId}")
    public List<TimeSlotDTO> getTimeSlotsForFoodOption(@PathVariable("foodId") Long foodId) {
        return timeSlotService.getTimeSlotsForFoodOption(foodId);
    }

    @PostMapping
    public @ResponseBody TimeSlotDTO addTimeSlotToFoodOption
            (@CookieValue(value = "session", defaultValue = "1") String token,
             @RequestBody FoodOptionDTO foodOptionDTO, @RequestBody TimeSlotDTO timeSlotDTO) {
        long userID = sessionService.getUserId(token);
        UserEntity currentUser = userService.findById(userID);
        return timeSlotService.addTimeSlotToFoodOption(timeSlotDTO, foodOptionService
                .getFoodOptionByName(foodOptionDTO.getName()), currentUser);
    }
//    public @ResponseBody TimeSlotDTO addTimeSlotToFoodOption
//            (@CookieValue(value = "session", defaultValue = "1") String token,
//    @RequestBody FoodOptionDTO foodOptionDTO, @RequestBody TimeSlotDTO timeSlotDTO) {
////        Random random = new Random();
////        int randomNumber = random.nextInt(10000 + 1);
////        String randNumber = Integer.toString(randomNumber);
//        UserEntity currentUser = new SessionService ("user2323" + randNumber, "password23");
//        // TODO sessionService
//        return timeSlotService.addTimeSlotToFoodOption(timeSlotDTO, foodOptionService
//                .getFoodOptionByName(foodOptionDTO.getName()), currentUser);
//    }

    @PutMapping
    public @ResponseBody TimeSlotDTO updateExistingTimeSlot
            (@CookieValue(value = "session", defaultValue = "1") String token,
             @RequestBody TimeSlotDTO oldTimeSlotDTO) {
            long userID = sessionService.getUserId(token);
            UserEntity currentUser = userService.findById(userID);
        return timeSlotService.update(currentUser, oldTimeSlotDTO);
    }
}
