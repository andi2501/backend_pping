package de.academy.backend_pping.buddy_core.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    private UserDTO userDTO;
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> registerUser(@RequestBody UserDTO userDtoReceived){

        if (userService.existsByUsername(userDtoReceived.getUsername())) {
            // Return an error response to the frontend
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }


       UserEntity newUser = userService.registerUser(userDtoReceived.getUsername(), userDtoReceived.getPassword());

       UserDTO userDtoRegistered = new UserDTO(newUser.getUsername(), newUser.getPassword(), newUser.getId());
       userDtoRegistered.setPassword(null);

       return new ResponseEntity<>(userDtoRegistered, HttpStatus.OK);
    }
}
