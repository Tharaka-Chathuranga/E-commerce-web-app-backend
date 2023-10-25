package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.LoginBody;
import com.example.eCommercewebapp.api.model.RegistrationBody;
import com.example.eCommercewebapp.api.model.UserDetailsEditBody;
import com.example.eCommercewebapp.exception.UserAlreadyExistsException;
import com.example.eCommercewebapp.model.FileData;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.FileDataDAO;
import com.example.eCommercewebapp.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private JwtService jwtService;
    private EncryptionService encryptionService;
    private UserDAO userDAO;

    private FileDataDAO fileDataDAO;
    @Autowired
    public UserService(JwtService jwtService, EncryptionService encryptionService, UserDAO userDAO, FileDataDAO fileDataDAO) {
        this.jwtService = jwtService;
        this.encryptionService = encryptionService;
        this.userDAO = userDAO;
        this.fileDataDAO = fileDataDAO;
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
        System.out.println(luser.isPresent());
        if (luser.isPresent()){
            User user = luser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

    public User addProfilePhoto(User user, FileData fileData){
        FileData exsistingFileData = fileDataDAO.findById(fileData.getId()).get();
        System.out.println(exsistingFileData);
        User exsistingUser = userDAO.findById(user.getId()).get();
        exsistingUser.setFileData(exsistingFileData);
        userDAO.save(exsistingUser);

    return exsistingUser;
    }

    public User editUserDetails(User user, UserDetailsEditBody userDetailsEditBody){
        User exsistingUser = userDAO.findById(user.getId()).get();

        if (exsistingUser == null){
            return null;
        }
        else{
            exsistingUser.setFirstname(userDetailsEditBody.getFirstname());
            exsistingUser.setLastname(userDetailsEditBody.getLastname());
            exsistingUser.setEmail(userDetailsEditBody.getUsername());
            exsistingUser.setAddress(userDetailsEditBody.getAddress());

            userDAO.save(exsistingUser);
            return exsistingUser;
        }
    }
}
