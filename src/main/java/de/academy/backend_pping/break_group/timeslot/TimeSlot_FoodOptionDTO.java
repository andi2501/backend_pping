package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;

public class TimeSlot_FoodOptionDTO {

    private TimeSlotDTO timeSlotDTO;
    private FoodOptionDTO foodOptionDTO;

    public TimeSlot_FoodOptionDTO() {
    }

    public TimeSlot_FoodOptionDTO(TimeSlotDTO timeSlotDTO, FoodOptionDTO foodOptionDTO) {
        this.timeSlotDTO = timeSlotDTO;
        this.foodOptionDTO = foodOptionDTO;
    }

    public TimeSlotDTO getTimeSlotDTO() {
        return timeSlotDTO;
    }

    public void setTimeSlotDTO(TimeSlotDTO timeSlotDTO) {
        this.timeSlotDTO = timeSlotDTO;
    }

    public FoodOptionDTO getFoodOptionDTO() {
        return foodOptionDTO;
    }

    public void setFoodOptionDTO(FoodOptionDTO foodOptionDTO) {
        this.foodOptionDTO = foodOptionDTO;
    }
}
