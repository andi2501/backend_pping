package de.academy.backend_pping.buddy_core.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);

}
