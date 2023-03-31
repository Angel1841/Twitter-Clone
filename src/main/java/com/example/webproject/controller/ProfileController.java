package com.example.webproject.controller;

import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    private final AuthService authService;
    private final TweetService tweetService;

    public ProfileController(AuthService authService, TweetService tweetService) {
        this.authService = authService;
        this.tweetService = tweetService;
    }

    @PostMapping("/profile")
    public String profile(Principal principal, Model model) {

        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        UserProfileDTO userProfileDTO = new UserProfileDTO(
                username,
                user.getEmail(),
                user.getRoles(),
                user.getLikes(),
                user.getTweets(),
                user.getRetweets()
        );

        model.addAttribute("user", userProfileDTO);
        model.addAttribute("myTweets", tweetService.getTweetsByUsername(username));

        for (var role: userProfileDTO.getRoles()){
            System.out.println(role.getRole().name());
        }

        return "profile";
    }

}
