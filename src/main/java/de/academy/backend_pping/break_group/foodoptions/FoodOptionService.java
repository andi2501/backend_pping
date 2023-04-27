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

    public FoodOptionDTO getFoodOptionByName(String name) {
        FoodOption foodOption = foodOptionRepository.getFoodOptionByName(name);
        return new FoodOptionDTO(foodOption);
    }

    public List<FoodOptionDTO> getAllFoodOptions() {
        List<FoodOption> foodOptions = (List<FoodOption>) foodOptionRepository.findAll();
        List<FoodOptionDTO> result = new ArrayList<>();
        for (FoodOption option : foodOptions) {
            result.add(new FoodOptionDTO(option));
        }
        return result;
    }

    public FoodOptionDTO save(FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionRepository.save(new FoodOption(foodOptionDTO));
        return new FoodOptionDTO(foodOption);
    }

    public void delete(Long id) {
        foodOptionRepository.deleteById(id);
    }

    public FoodOptionDTO update(Long id, FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionRepository.findById(id).get();
        foodOption.updateFoodOption(foodOptionDTO);
        return new FoodOptionDTO( foodOptionRepository.save(foodOption));
    }
}
