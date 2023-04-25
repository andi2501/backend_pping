package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    // creates a new token with each successful login and saves it to the database
    public SessionEntity authenticate(String username, String password) {

        if(isValidUser(username, password)) {
            SessionEntity session = new SessionEntity();
            session.setToken(generateToken());
            session.setUserId(userRepository.findByUsername(username).orElse(null).getId());
            session.setExpirationTime(LocalDateTime.now().plusMinutes(7*24*60*60));
            return sessionRepository.save(session);

        }
        return null;
    }
    /*public String authenticate(String username, String password) {

        if(isValidUser(username, password)) {
            SessionEntity session = new SessionEntity();
            session.setToken(generateToken());
            session.setUserId(userRepository.findByUsername(username).orElse(null).getId());
            session.setExpirationTime(LocalDateTime.now().plusMinutes(7*24*60*60));
            sessionRepository.save(session);
            return session.getToken();
        }
        return null;
    }*/

    // deletes session from database
    public boolean invalidate(String token) {
        Optional<SessionEntity> optionalSession = sessionRepository.findByToken(token);
        if (optionalSession.isPresent()) {
            SessionEntity session = optionalSession.get();
            sessionRepository.delete(session);
            return true;
        }
        return false;
    }

    public boolean isValidUser(String username, String password) {

        Optional<UserEntity> optionalUser = userRepository.findByUsernameAndPassword(username, password);
        return optionalUser.isPresent();
    }

    private String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
