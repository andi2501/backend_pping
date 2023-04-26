package de.academy.backend_pping;
import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionRepository;
import de.academy.backend_pping.break_group.timeslot.TimeSlot;
import de.academy.backend_pping.break_group.timeslot.TimeSlotDTO;
import de.academy.backend_pping.break_group.timeslot.TimeSlotRepository;
import de.academy.backend_pping.break_group.timeslot.TimeSlotService;
import de.academy.backend_pping.buddy_core.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;



public class TimeSlotServiceTest {

    @Mock
    private TimeSlotService timeSlotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTimeSlots_shouldReturnListOfTimeSlotDTOs() {
        List<TimeSlotDTO> expectedTimeSlots = new ArrayList<>();
        expectedTimeSlots.add(new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>()));

        when(timeSlotService.getAllTimeSlots()).thenReturn(expectedTimeSlots);

        List<TimeSlotDTO> actualTimeSlots = timeSlotService.getAllTimeSlots();

        assertEquals(expectedTimeSlots, actualTimeSlots);
    }

    @Test
    void getTimeSlotsForFoodOption_shouldReturnListOfTimeSlotDTOs() {
        Long foodOptionId = 1L;
        List<TimeSlotDTO> expectedTimeSlots = new ArrayList<>();
        expectedTimeSlots.add(new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>()));

        when(timeSlotService.getTimeSlotsForFoodOption(foodOptionId)).thenReturn(expectedTimeSlots);

        List<TimeSlotDTO> actualTimeSlots = timeSlotService.getTimeSlotsForFoodOption(foodOptionId);

        assertEquals(expectedTimeSlots, actualTimeSlots);
    }

    @Test
    void addTimeSlotToFoodOption_shouldReturnTimeSlotDTO() {
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>());
        FoodOptionDTO foodOptionDTO = new FoodOptionDTO();
        UserEntity currentUser = new UserEntity();
        TimeSlotDTO expectedTimeSlot = new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>());

        when(timeSlotService.addTimeSlotToFoodOption(timeSlotDTO, foodOptionDTO, currentUser)).thenReturn(expectedTimeSlot);

        TimeSlotDTO actualTimeSlot = timeSlotService.addTimeSlotToFoodOption(timeSlotDTO, foodOptionDTO, currentUser);

        assertEquals(expectedTimeSlot, actualTimeSlot);
    }

    @Test
    void update_shouldReturnTimeSlotDTO() {
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>());
        UserEntity currentUser = new UserEntity();
        TimeSlotDTO expectedTimeSlot = new TimeSlotDTO(1L, new Time(System.currentTimeMillis()), new FoodOptionDTO(), new ArrayList<>());

        when(timeSlotService.update(currentUser, timeSlotDTO)).thenReturn(expectedTimeSlot);

        TimeSlotDTO actualTimeSlot = timeSlotService.update(currentUser, timeSlotDTO);

        assertEquals(expectedTimeSlot, actualTimeSlot);
    }
}