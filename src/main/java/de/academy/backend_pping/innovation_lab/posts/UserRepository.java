package de.academy.backend_pping.innovation_lab.posts;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findUserById(long id);

}
