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
}
