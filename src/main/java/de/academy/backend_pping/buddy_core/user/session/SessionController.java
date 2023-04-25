package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserDTO;
import de.academy.backend_pping.buddy_core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class SessionController {

    private SessionService sessionService;
    private UserService userService;

    @Autowired
    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
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
            return new ResponseEntity<>("du warst nicht eingeloggt", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("logout ok", HttpStatus.OK);
    }
}
