package com.example.webproject.wrb;

import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final AuthService authService;

    private final TweetService tweetService;

    private final UserRepository userRepository;

    @Autowired
    public HomeController(AuthService authService, TweetService tweetService, UserRepository userRepository) {
        this.authService = authService;
        this.tweetService = tweetService;
        this.userRepository = userRepository;
    }



    @GetMapping("/home")
    public String homePage(Model model, Principal principal){

        model.addAttribute("Tweets", tweetService.getAllTweets());


        String username = principal.getName();
        UserEntity user = getUser(username);

        model.addAttribute("currentUser", user);

        boolean isAdmin = user.isAdmin();

        model.addAttribute("isAdmin", isAdmin);


        return "/home";
    }

    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }



}
