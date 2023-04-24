package de.academy.backend_pping.buddy_core.user.session;

import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Optional;

public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
    Optional<SessionEntity> findByToken(String token);
}
