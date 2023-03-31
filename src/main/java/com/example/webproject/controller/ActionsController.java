package com.example.webproject.controller;

import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ActionsController {

    private final AuthService authService;

    @GetMapping("/liked")
    public String likedTweets(Model model, Principal principal){

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

        return "/liked";
    }



}
