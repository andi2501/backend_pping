package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tag {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private FoodOption foodOption;

    private String name;

    public Tag() {
    }

    public Tag(FoodOption foodOption, String name) {
        this.foodOption = foodOption;
        this.name = name;
    }

    public Tag(Long id, FoodOption foodOption, String name) {
        this.id = id;
        this.foodOption = foodOption;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public FoodOption getFoodOption() {
        return foodOption;
    }

    public void setFoodOption(FoodOption foodOption) {
        this.foodOption = foodOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
