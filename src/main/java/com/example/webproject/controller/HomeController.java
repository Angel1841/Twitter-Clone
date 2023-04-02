package com.example.webproject.controller;

import com.example.webproject.model.DTOS.LikeRetweetDTO;
import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;

    private final TweetService tweetService;



    @Autowired
    public HomeController(AuthService authService, TweetService tweetService) {
        this.authService = authService;
        this.tweetService = tweetService;
    }



    @GetMapping("/home")
    public String homePage(Model model, Principal principal){

        model.addAttribute("Tweets", tweetService.getAllTweets());


        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        model.addAttribute("currentUser", user);

        boolean isAdmin = user.isAdmin();

        model.addAttribute("isAdmin", isAdmin);


        return "/home";
    }



}
