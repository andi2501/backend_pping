package de.academy.backend_pping.buddy_core.user;

import org.springframework.context.annotation.Bean;

public class UserDTO {

    private String username;
    private String password;
    private long id;

    public UserDTO() {
    }

    public UserDTO(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.id = userEntity.getId();
    }

    public UserDTO(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
