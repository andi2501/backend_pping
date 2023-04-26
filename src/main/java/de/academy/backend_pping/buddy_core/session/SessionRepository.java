package de.academy.backend_pping.buddy_core.session;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
    Optional<SessionEntity> findByToken(String token);

}
