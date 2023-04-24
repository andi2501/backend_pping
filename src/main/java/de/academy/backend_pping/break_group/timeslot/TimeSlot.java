package de.academy.backend_pping.break_group.timeslot;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.buddy_core.user.UserEntity;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue
    private Long id;

    private Time time;

    @ManyToOne
    private FoodOption foodOption;

    @OneToMany
    private List<UserEntity> users;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public FoodOption getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(FoodOption foodOption) {
        this.foodOption = foodOption;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
