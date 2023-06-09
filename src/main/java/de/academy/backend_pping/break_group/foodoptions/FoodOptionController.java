package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/break/foodoption")
public class FoodOptionController {

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping
    public List<FoodOptionDTO> breakStarter() {
        return foodOptionService.getAllFoodOptions();
    }

    @GetMapping("/{id}")
    public FoodOptionDTO getFoodOptionById(@PathVariable("id") Long id) {
        return foodOptionService.getFoodOptionsById(id);
    }

    @PostMapping
    public @ResponseBody FoodOptionDTO createFoodOption(@RequestBody FoodOptionDTO foodOptionDTO) {
        return foodOptionService.save(foodOptionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodOption(@PathVariable("id") Long id) {
        foodOptionService.delete(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody FoodOptionDTO updateFoodOption(@PathVariable("id") Long id, FoodOptionDTO foodOptionDTO) {
        return foodOptionService.update(id, foodOptionDTO);
    }
}