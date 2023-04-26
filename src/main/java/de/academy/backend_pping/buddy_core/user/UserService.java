package de.academy.backend_pping.buddy_core.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = new UserEntity(username, encodedPassword);
        return userRepository.save(newUser);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();

    }
}
