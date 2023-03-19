package com.example.webproject.controller;

import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private AuthService authService;

    @Autowired
    public HomeController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/home")
    public String homePage(Model model){
        return "/home";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        UserProfileDTO userProfileDTO = new UserProfileDTO(
                username,
                user.getEmail(),
                user.getRoles(),
                user.getComments(),
                user.getTweets()
        );

        model.addAttribute("user", userProfileDTO);

        return "profile";
    }

}
