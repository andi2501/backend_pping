package de.academy.backend_pping.buddy_core.user.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class SessionControllerAdvice {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionControllerAdvice(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // TODO: einfügen einer "sessionUser"-Methode mit Annotation @ModelAttribute
    // Dies, um sicherzustellen, dass der "sessionUser" auf jeder Seite verfügbar ist, wenn ein User eingeloggt ist.
}
