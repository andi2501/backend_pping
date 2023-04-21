package de.academy.backend_pping.buddy_core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO Cori: Hashing des Passworts

    public UserEntity registerUser(String username, String password) {
        UserEntity newUser = new UserEntity(username, password);
        userRepository.save(newUser);
        return newUser;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
