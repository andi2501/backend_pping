package de.academy.backend_pping.buddy_core.user;

import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody UserDTO registerUser(@RequestBody UserDTO userDtoReceived){

       UserEntity newUser = userService.registerUser(userDtoReceived.getUsername(), userDtoReceived.getPassword());

       UserDTO userDtoRegistered = new UserDTO(newUser.getUsername(), newUser.getPassword(), newUser.getId());
       userDtoRegistered.setPassword(null);

       return userDtoRegistered;
    }
}
