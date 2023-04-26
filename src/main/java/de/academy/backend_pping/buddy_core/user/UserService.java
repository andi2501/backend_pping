package de.academy.backend_pping.buddy_core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String username, String password) {
        UserEntity newUser = new UserEntity(username, password);
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

    public List<UserEntity> findAll(){
        return userRepository.findAll();

    }
}
