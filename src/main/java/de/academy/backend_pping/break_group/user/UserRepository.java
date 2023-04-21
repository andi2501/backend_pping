package de.academy.backend_pping.break_group.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

//    List<UserEntity> findAll();

}
