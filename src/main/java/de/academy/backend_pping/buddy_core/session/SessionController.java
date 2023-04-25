package de.academy.backend_pping.buddy_core.session;

import de.academy.backend_pping.buddy_core.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

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
        return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
    }

    @GetMapping ("/logout-user")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (!sessionService.invalidate(token)) {
            return new ResponseEntity<>("Du warst nicht eingeloggt!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Du bist ausgeloggt!", HttpStatus.OK);
    }
}
