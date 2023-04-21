package de.academy.backend_pping.buddy_core.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

//    @ManyToMany
//    private List<UserEntity> friends;
//
//    private boolean isAdmin;

    public UserEntity(){}

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId(){
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

//    public List<UserEntity> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<UserEntity> friends) {
//        this.friends = friends;
//    }
//
//    public boolean isAdmin() {
//        return isAdmin;
//    }
//
//    public void setAdmin(boolean admin) {
//        isAdmin = admin;
//    }
}
