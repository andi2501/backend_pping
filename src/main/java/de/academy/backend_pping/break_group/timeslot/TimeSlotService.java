package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionRepository;
import de.academy.backend_pping.buddy_core.user.UserEntity;
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

    /**
     * READ
     * find all time slots in the database
     * @return List<TimeSlotDTO>
     */
    public List<TimeSlotDTO> getAllTimeSlots() {
        List<TimeSlot> timeSlots = (List<TimeSlot>) timeSlotRepository.findAll();
        return turnEntityToDto(timeSlots);
    }

    /**
     * READ
     * get all timeSlots that are associated with a specific foodOptions from the database by using the foodOptions Id
     * @param foodId
     * @return List<TimeSlotDTO>
     */
    public List<TimeSlotDTO> getTimeSlotsForFoodOption(Long foodId) {
        FoodOption foodOption = foodOptionRepository.findById(foodId).get();
        List<TimeSlot> timeSlots = timeSlotRepository.findAllByFoodOption(foodOption);
        return turnEntityToDto(timeSlots);
    }

    /**
     * CREATE
     * add new timeSlot insatnce to food option
     * @param timeSlotDTO
     * @param foodOption
     * @return TimeSlotDTO
     */
    public TimeSlotDTO addTimeSlotToFoodOption(TimeSlotDTO timeSlotDTO, FoodOptionDTO foodOption, UserEntity currentUser) {
        List<UserEntity> users = new ArrayList<>();
        users.add(currentUser);
        TimeSlot newTimeSlot = timeSlotRepository.save(
                new TimeSlot(timeSlotDTO.getTime(),
                        foodOptionRepository.getFoodOptionByName(timeSlotDTO.getFoodOption().getName()),
                        users)
        );
        return new TimeSlotDTO(newTimeSlot);
    }


    /**
     * UPDATE
     * updates an existing instance of timeSlot by adding the current user to it
     * @param currentUser
     * @param timeSlotDTO
     * @return TimeSlotDTO
     */
    public TimeSlotDTO update(UserEntity currentUser, TimeSlotDTO timeSlotDTO) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotDTO.getId()).get();
        List<UserEntity> users = timeSlot.getUsers();
        users.add(currentUser);
        timeSlot.setUsers(users);
        timeSlotRepository.save(timeSlot);
        return new TimeSlotDTO(timeSlot);
    }


    /**
     * turns list of entities into list of dtos
     * @param timeSlots
     * @return List<TimeSlotDTO>
     */
    private List<TimeSlotDTO> turnEntityToDto(List<TimeSlot> timeSlots) {
        List<TimeSlotDTO> timeSlotDTOS = new ArrayList<>();
        for (TimeSlot timeSlot : timeSlots) {
            timeSlotDTOS.add(new TimeSlotDTO(timeSlot));
        }
        return timeSlotDTOS;
    }
}
