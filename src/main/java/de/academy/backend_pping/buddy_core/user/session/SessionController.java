package de.academy.backend_pping.buddy_core.user.session;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/login")
public class SessionController {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public @ResponseBody LoginDTO loginUser(@RequestBody LoginDTO loginDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByUsernameAndPassword(loginDTO.getUsername(),
                loginDTO.getPassword());

        return loginDTO;
    }
}
