package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.LoginBody;
import com.example.eCommercewebapp.api.model.RegistrationBody;
import com.example.eCommercewebapp.exception.UserAlreadyExistsException;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private JwtService jwtService;
    private EncryptionService encryptionService;
    private UserDAO userDAO;
    @Autowired
    public UserService(JwtService jwtService, EncryptionService encryptionService, UserDAO userDAO) {
        this.jwtService = jwtService;
        this.encryptionService = encryptionService;
        this.userDAO = userDAO;
    }

    public User registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (userDAO.findByEmailIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User()  ;
        user.setFirstname(registrationBody.getFirstname());
        user.setLastname(registrationBody.getLastname());
        user.setEmail(registrationBody.getUsername());
        user.setAddress(registrationBody.getAddress());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setRole(registrationBody.getRole());
        userDAO.save(user);
        return user;
    }

    public String loginUser(LoginBody loginBody){
        Optional<User> luser = userDAO.findByEmailIgnoreCase(loginBody.getUsername());
        if (luser.isPresent()){
            User user = luser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
