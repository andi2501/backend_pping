package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("api/break")
public class FoodOptionController {

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping("/foodoption")
    public List<FoodOption> breakStarter() {
        return foodOptionService.getAllFoodOptions();
    }

//    @GetMapping("/foodoption/{id}")

    @PostMapping("/foodoption")
    public @ResponseBody FoodOptionDTO createFoodOption(@RequestBody FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionService.save(foodOptionDTO);
        return new FoodOptionDTO(foodOption);
    }

    @DeleteMapping("/foodoption/{id}")
    public void deleteFoodOption(@PathVariable("id") Long id) {
        foodOptionService.delete(id);
    }

    @PutMapping("/foodoption/{id}")
    public @ResponseBody FoodOptionDTO updateFoodOption(@PathVariable("id") Long id, FoodOptionDTO foodOptionDTO) {
        FoodOption foodOption = foodOptionService.update(id, foodOptionDTO);
        return new FoodOptionDTO(foodOption);
    }
}