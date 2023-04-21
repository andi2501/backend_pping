package de.academy.backend_pping.buddy_core.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity {

    // Attributes, Primary Key
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private boolean isAdmin;

    private boolean innovationLabActive;

    private boolean isAnonymous;

    private int achievementPoints;

    // Constructors
    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getter und Setter
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isInnovationLabActive() {
        return innovationLabActive;
    }

    public void setInnovationLabActive(boolean innovationLabActive) {
        this.innovationLabActive = innovationLabActive;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }

    public void setAchievementPoints(int achievementPoints) {
        this.achievementPoints = achievementPoints;
    }
}
