package com.example.userservice.vo;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {
    @NotNull(message = "Enter your email")
    @Email(message = "Wrong or Invalid email address or mobile phone number. Please correct and try again.")
    private String email;

    @NotNull(message = "Enter your name")
    private String name;

    @NotNull(message = "Minimum 6 characters required")
    @Size(min = 6, message = "Minimum 6 characters required")
    private String password;
}
