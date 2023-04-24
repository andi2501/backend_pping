package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    public List<TimeSlotDTO> getAllTimeSlots() {
        List<TimeSlotDTO> timeSlotDTOS = new ArrayList<>();
        List<TimeSlot> timeSlots = (List<TimeSlot>) timeSlotRepository.findAll();
        for (TimeSlot timeSlot : timeSlots) {
            timeSlotDTOS.add(new TimeSlotDTO(timeSlot));
        }
        return timeSlotDTOS;
    }

    public List<TimeSlotDTO> getTimeSlotsForFoodOption(Long foodId) {
        FoodOption foodOption = foodOptionRepository.findById(foodId).get();
        List<TimeSlot> timeSlots = timeSlotRepository.findAllByFoodOption(foodOption);
        return turnEntityToDto(timeSlots);
    }

    private List<TimeSlotDTO> turnEntityToDto(List<TimeSlot> timeSlots) {
        List<TimeSlotDTO> timeSlotDTOS = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            timeSlotDTOS.add(new TimeSlotDTO(timeSlot));
        }
        return timeSlotDTOS;
    }
}
