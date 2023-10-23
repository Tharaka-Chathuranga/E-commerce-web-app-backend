package com.example.eCommercewebapp.api.controller.user;

import com.example.eCommercewebapp.api.model.RegistrationBody;
import com.example.eCommercewebapp.exception.UserAlreadyExistsException;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/adminUser")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        System.out.println(registrationBody);
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
