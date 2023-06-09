package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/break/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping
    public List<TagDTO> getTagsForFoodOption(@RequestBody FoodOptionDTO foodOptionDTO) {
        return tagService.getTagsForFoodOption(foodOptionService
                .getFoodOptionByName(foodOptionDTO.getName()));
    }

    @PostMapping
    public @ResponseBody TagDTO addTagToFoodOption(@RequestBody TagDTO tagDTO) {
        return tagService.addTagForFoodOption(tagDTO, foodOptionService
                .getFoodOptionByName(tagDTO.getFoodOption().getName()));
    }


    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable("tagId") Long id) {
        tagService.deleteTag(id);
    }
}