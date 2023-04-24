package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;

import javax.persistence.ManyToOne;

public class TagDTO {

    private Long id;
    private FoodOptionDTO foodOption;
    private String name;

    public TagDTO() {
    }

    public TagDTO(Long id, FoodOptionDTO foodOption, String name) {
        this.id = id;
        this.foodOption = foodOption;
        this.name = name;
    }

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.foodOption = new FoodOptionDTO( tag.getFoodOption());
        this.name = tag.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodOptionDTO getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(FoodOptionDTO foodOption) {
        this.foodOption = foodOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
