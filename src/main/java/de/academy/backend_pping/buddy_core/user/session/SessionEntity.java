package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserEntity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class SessionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @Column
    private LocalDateTime expirationTime;

    private Long userId;

    public SessionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}