package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;

@Entity
public class SessionEntity {

    // Erzeugung einer zufälligen Session-ID, die in DB gespeichert wird (SessionController)
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private UserEntity user;

    private Instant expiresAt;

    public SessionEntity() {
    }

    public SessionEntity(UserEntity user, Instant expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
