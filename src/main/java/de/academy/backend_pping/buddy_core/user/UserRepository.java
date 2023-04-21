package de.academy.backend_pping.buddy_core.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

//    List<UserEntity> findAll();

}
