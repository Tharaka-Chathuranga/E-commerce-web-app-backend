package com.example.eCommercewebapp.api.controller.auth;

import com.example.eCommercewebapp.api.model.LoginBody;
import com.example.eCommercewebapp.api.model.LoginResponse;
import com.example.eCommercewebapp.api.model.RegistrationBody;
import com.example.eCommercewebapp.exception.UserAlreadyExistsException;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin( "http://localhost:5173")
public class AuthenticationController {
    private UserService userService;
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        System.out.println(registrationBody);
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        }catch(UserAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        System.out.println(loginBody);
        String jwt = userService.loginUser(loginBody);
        if (jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else{
            LoginResponse response= new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){
        return user;
    }

}
