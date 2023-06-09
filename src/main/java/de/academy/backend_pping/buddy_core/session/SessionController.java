package de.academy.backend_pping.buddy_core.session;

import de.academy.backend_pping.buddy_core.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        SessionEntity sessionEntity = sessionService.authenticate(username, password);
        String token = sessionEntity.getToken();
        if (token == null) {
            return new ResponseEntity<>("Login fehlgeschlagen", HttpStatus.BAD_REQUEST);
        }
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSessionToken(token);
        sessionDTO.setUserId(sessionEntity.getUserId());

        Cookie cookie = new Cookie("session", sessionDTO.getSessionToken());
        response.addCookie(cookie);

        return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
    }

    @GetMapping ("/logout-user")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if (!sessionService.invalidate(token)) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

        Cookie cookie = new Cookie("session", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
