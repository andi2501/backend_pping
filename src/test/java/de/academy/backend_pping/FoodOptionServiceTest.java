package de.academy.backend_pping;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionRepository;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @SpringBootTest
    public class FoodOptionServiceTest {

        @Mock
        private FoodOptionRepository foodOptionRepository;

        @InjectMocks
        private FoodOptionService foodOptionService;

        @Test
        public void testGetAllFoodOptions() {
            // Mock foodOptionRepository.findAll() to return a list of food options
            List<FoodOption> foodOptions = new ArrayList<>();
            foodOptions.add(new FoodOption(1L, "Peter Pane", "Goldbekpl. 1, 22303 Hamburg", "peterpane.de", "1 Stunde", 20));
            foodOptions.add(new FoodOption(2L, "SVAAdish", "Poßmoorweg 6, 22301 Hamburg", "svaadish.de", "30 Minuten", 15));
            when(foodOptionRepository.findAll()).thenReturn(foodOptions);

            // Call the method being tested
            List<FoodOptionDTO> result = foodOptionService.getAllFoodOptions();

            // Verify that the correct list of food options was returned
            assertEquals(2, result.size());
            assertEquals("Peter Pane", result.get(0).getName());
            assertEquals("SVAAdish", result.get(1).getName());
        }

        @Test
        public void testSave() {
            // Mock foodOptionRepository.save() to return a food option
            FoodOptionDTO foodOptionDTO = new FoodOptionDTO(1L, "Peter Pane", "Goldbekpl. 1, 22303 Hamburg", "peterpane.de", "1 Stunde", 20);
            FoodOption savedFoodOption = new FoodOption(1L, "Peter Pane", "Goldbekpl. 1, 22303 Hamburg", "peterpane.de", "1 Stunde", 20);
            when(foodOptionRepository.save(any(FoodOption.class))).thenReturn(savedFoodOption);

            // Call the method being tested
            FoodOptionDTO result = foodOptionService.save(foodOptionDTO);

            // Verify that the correct food option was returned
            assertEquals(1L, result.getId());
            assertEquals("Peter Pane", result.getName());
        }

        @Test
        public void testDelete() {
            // Call the method being tested
            foodOptionService.delete(1L);

            // Verify that foodOptionRepository.deleteById() was called with the correct ID
            verify(foodOptionRepository, times(1)).deleteById(1L);
        }

        @Test
        public void testFoodUpdate() {
            // Mock foodOptionRepository.findById() to return a food option
            FoodOptionDTO foodOptionDTO = new FoodOptionDTO(1L, "Peter Planet", "Goldbekpl. 8, 22303 Hamburg", "peterpane.de", "1 Stunde", 20);
            FoodOption existingFoodOption = new FoodOption(1L, "Peter Pane", "Goldbekpl. 1, 22303 Hamburg", "peterpane.de", "1 Stunde", 20);
            when(foodOptionRepository
                    .findById(1L))
                    .thenReturn(Optional.of(existingFoodOption));
            when(foodOptionRepository
                    .save(any(FoodOption.class)))
                    .thenReturn(existingFoodOption);

            // Call the method being tested
            FoodOptionDTO result = foodOptionService.update(1L, foodOptionDTO);

            // Verify that the correct food option was returned
            assertEquals(1L, result.getId());
            assertEquals("Peter Planet", result.getName());
        }
    }

