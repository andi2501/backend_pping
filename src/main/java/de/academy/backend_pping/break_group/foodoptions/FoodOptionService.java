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

    public FoodOption save(FoodOptionDTO foodOptionDTO) {
        return foodOptionRepository.save(new FoodOption(foodOptionDTO));
    }

    public void delete(Long id) {
        foodOptionRepository.deleteById(id);
    }

    public FoodOption update(Long id, FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionRepository.findById(id).get();
        foodOption.updateFoodOption(foodOptionDTO);
        return foodOptionRepository.save(foodOption);
    }
}
