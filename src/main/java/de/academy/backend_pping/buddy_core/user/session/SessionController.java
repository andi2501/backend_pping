package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SessionController {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    // TODO: http-Methodenimplementierung "login", "logout"
}
