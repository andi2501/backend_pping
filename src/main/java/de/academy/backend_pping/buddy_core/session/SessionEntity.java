package de.academy.backend_pping.buddy_core.session;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class SessionEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

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