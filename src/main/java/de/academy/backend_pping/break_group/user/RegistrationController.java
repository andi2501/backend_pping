package de.academy.backend_pping.break_group.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody UserDTO registerUser(UserDTO userDTO){

        userService.registerUser(userDTO.getUsername(), userDTO.getPassword());

        return userDTO;
    }

}
