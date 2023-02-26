package com.example.webproject.controller;

import com.example.webproject.entity.DTOS.UserLoginDTO;
import com.example.webproject.entity.DTOS.UserRegistrationDTO;
import com.example.webproject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return new UserRegistrationDTO();
    }


    @ModelAttribute("loginDTO")
    public UserLoginDTO initLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register(){
        if(this.authService.isLogged()) {
            return "redirect:/home";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        if(this.authService.isLogged()) {
            return "redirect:/home";
        }
        return "/login";
    }

}
