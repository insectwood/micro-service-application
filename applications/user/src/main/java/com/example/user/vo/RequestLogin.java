package com.example.user.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "Enter your email")
    @Email
    private String email;

    @NotNull(message = "Minimum 6 characters required")
    @Size(min = 6, message = "Minimum 6 characters required")
    private String password;
}
