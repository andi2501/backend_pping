package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    public FoodOptionDTO getFoodOptionsById(Long id) {
        FoodOption foodOption = foodOptionRepository.findById(id).get();
        return new FoodOptionDTO(foodOption);
    }

    public FoodOption getFoodOptionByName(String name) {
        return foodOptionRepository.getFoodOptionByName(name);
    }

    public List<FoodOptionDTO> getAllFoodOptions() {
        List<FoodOption> foodOptions = (List<FoodOption>) foodOptionRepository.findAll();
        List<FoodOptionDTO> result = new ArrayList<>();
        for (FoodOption option : foodOptions) {
            result.add(new FoodOptionDTO(option));
        }
        return result;
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
