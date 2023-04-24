package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.buddy_core.user.UserEntity;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.util.List;

public class TimeSlotDTO {

    private Long id;

    private Time time;
    private FoodOptionDTO foodOption;
//    private List<UserEntity> users;


    public TimeSlotDTO() {
    }

    public TimeSlotDTO(Long id, Time time, FoodOptionDTO foodOption) {
        this.id = id;
        this.time = time;
        this.foodOption = foodOption;
    }

    public TimeSlotDTO(TimeSlot timeSlot) {
        this.id = timeSlot.getId();
        this.time = timeSlot.getTime();
        this.foodOption = new FoodOptionDTO(timeSlot.getFoodOption());
//        this.users = new UserDTO(timeSlot.getUsers());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public FoodOptionDTO getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(FoodOptionDTO foodOption) {
        this.foodOption = foodOption;
    }
}
