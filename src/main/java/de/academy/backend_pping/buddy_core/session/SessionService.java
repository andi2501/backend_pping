package de.academy.backend_pping.buddy_core.session;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // create a new token with each successful login and save it to the database
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

    // delete session from database
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

        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if(user!= null)
        {
            return passwordEncoder.matches(password, user.getPassword());
        }
        else {
            return false;
        }
    }

    private String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public long getUserId(String token) {

        SessionEntity sessionEntity = sessionRepository.findByToken(token).orElse(null);
        // Hint: Es sollte auch der Fall sessionEntity==null abgefangen werden
        return sessionEntity.getUserId();
    }
}
