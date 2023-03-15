package com.example.webproject.model.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters!")
    @NotBlank
    private String username;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters!")
    @NotBlank
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}
