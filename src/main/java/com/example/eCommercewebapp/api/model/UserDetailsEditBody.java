package com.example.eCommercewebapp.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsEditBody {

    @NotNull
    @NotBlank
    private String firstname;
    @NotNull
    @NotBlank
    private String lastname;

    @Email
    private String username;


    @NotNull
    @NotBlank
    private String address;

    private long telephone;
}
