package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    public List<FoodOption> getAllFoodOptions() {
        return (List<FoodOption>) foodOptionRepository.findAll();
    }
}
