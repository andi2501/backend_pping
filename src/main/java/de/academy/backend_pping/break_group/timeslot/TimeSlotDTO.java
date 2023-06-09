package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.buddy_core.user.UserDTO;
import de.academy.backend_pping.buddy_core.user.UserEntity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotDTO {

    private Long id;
    private Time time;
    private FoodOptionDTO foodOption;
    private List<UserDTO> users;


    public TimeSlotDTO() {
    }

    public TimeSlotDTO(Long id, Time time, FoodOptionDTO foodOption, List<UserDTO> users) {
        this.id = id;
        this.time = time;
        this.foodOption = foodOption;
        this.users = users;
    }

    public TimeSlotDTO(TimeSlot timeSlot) {
        this.id = timeSlot.getId();
        this.time = timeSlot.getTime();
        this.foodOption = new FoodOptionDTO(timeSlot.getFoodOption());
        this.users = new ArrayList<>();
        for (UserEntity user : timeSlot.getUsers()) {
            this.users.add(new UserDTO(user));
        }
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
