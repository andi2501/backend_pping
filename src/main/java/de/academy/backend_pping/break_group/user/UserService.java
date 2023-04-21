package de.academy.backend_pping.break_group.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String username, String password) {
        UserEntity newUser = new UserEntity(username, password);
        userRepository.save(newUser);
        return newUser;
    }

    // TODO Cori: Methode ob username existiert -> Christiane muss diese aus dem FE aufrufen um dort zu validieren
}
