package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("api/break")
public class FoodOptionController {

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping(path="/")
    public List<FoodOption> breakStarter() {
        return foodOptionService.getAllFoodOptions();
    }
}