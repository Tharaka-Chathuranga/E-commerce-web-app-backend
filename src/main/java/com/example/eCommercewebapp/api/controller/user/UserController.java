package com.example.eCommercewebapp.api.controller.user;

import com.example.eCommercewebapp.api.model.RegistrationBody;
import com.example.eCommercewebapp.api.model.UserDetailsEditBody;
import com.example.eCommercewebapp.exception.UserAlreadyExistsException;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin( "http://localhost:5173")

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
    @PutMapping("/editUserDetails")
    public ResponseEntity<User> editUserDetails(
            @AuthenticationPrincipal User user,
            @RequestBody UserDetailsEditBody userDetailsEditBody){

        try{
            User exsistingUser = userService.editUserDetails(user,userDetailsEditBody);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(exsistingUser);

        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/getTodayUsers")
    public ResponseEntity<List<User>> getTodayUsers(@AuthenticationPrincipal User user){
        System.out.println(user.getRole());
        if ("admin".equals(user.getRole())) {
            System.out.println("ho");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getTodayUser());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/getAllCustomers")
     public ResponseEntity<List<User>> getAllCustomers(@AuthenticationPrincipal User user){

        if ("admin".equals(user.getRole())) {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getAllCustomers());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
