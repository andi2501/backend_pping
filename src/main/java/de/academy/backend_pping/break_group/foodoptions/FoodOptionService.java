package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    /**
     * READ
     * finds a foodOption in the database by using the id attribute
     * @param id
     * @return foodOptionDTO
     */
    public FoodOptionDTO getFoodOptionsById(Long id) {
        FoodOption foodOption = foodOptionRepository.findById(id).get();
        return new FoodOptionDTO(foodOption);
    }

    /**
     * READ
     * finds a foodOption in the database by using the name attribute
     * @param name
     * @return FoodOptionDTO
     */
    public FoodOptionDTO getFoodOptionByName(String name) {
        FoodOption foodOption = foodOptionRepository.getFoodOptionByName(name);
        return new FoodOptionDTO(foodOption);
    }

    /**
     * READ
     * finds all available food options in the database
     * @return List<FoodOptionDTO>
     */
    public List<FoodOptionDTO> getAllFoodOptions() {
        List<FoodOption> foodOptions = (List<FoodOption>) foodOptionRepository.findAll();
        List<FoodOptionDTO> result = new ArrayList<>();
        for (FoodOption option : foodOptions) {
            result.add(new FoodOptionDTO(option));
        }
        return result;
    }

    /**
     * CREATE
     * saves foodOption to database
     * @param foodOptionDTO
     * @return FoodOptionDTO
     */
    public FoodOptionDTO save(FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionRepository.save(new FoodOption(foodOptionDTO));
        return new FoodOptionDTO(foodOption);
    }

    /**
     * DELETE
     * deletes foodOption from database by using the id attribute
     * @param id
     */
    public void delete(Long id) {
        foodOptionRepository.deleteById(id);
    }

    /**
     * UPDATE
     * updates the fields of a foodOption instance in the database according to the DTO-instance passed as parameter
     * @param id
     * @param foodOptionDTO
     * @return FoodOptionDTO
     */
    public FoodOptionDTO update(Long id, FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionRepository.findById(id).get();
        foodOption.updateFoodOption(foodOptionDTO);
        return new FoodOptionDTO( foodOptionRepository.save(foodOption));
    }
}
