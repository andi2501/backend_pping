package de.academy.backend_pping.break_group.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getTimeSlotsForFoodOption() {

        return new ArrayList<>();
    }
}
