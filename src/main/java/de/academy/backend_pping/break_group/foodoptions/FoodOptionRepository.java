package de.academy.backend_pping.break_group.foodoptions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOptionRepository extends CrudRepository<FoodOption, Long> {
    FoodOption getFoodOptionByName(String name);
}
