package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;

public class TagFoodDTO {

    private FoodOptionDTO foodOptionDTO;
    private TagDTO tagDTO;

    public TagFoodDTO() {
    }

    public TagFoodDTO(FoodOptionDTO foodOptionDTO, TagDTO tagDTO) {
        this.foodOptionDTO = foodOptionDTO;
        this.tagDTO = tagDTO;
    }

    public FoodOptionDTO getFoodOptionDTO() {
        return foodOptionDTO;
    }

    public void setFoodOptionDTO(FoodOptionDTO foodOptionDTO) {
        this.foodOptionDTO = foodOptionDTO;
    }

    public TagDTO getTagDTO() {
        return tagDTO;
    }

    public void setTagDTO(TagDTO tagDTO) {
        this.tagDTO = tagDTO;
    }
}
