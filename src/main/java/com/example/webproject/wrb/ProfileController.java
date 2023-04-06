package com.example.webproject.wrb;

import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    private final AuthService authService;
    private final TweetService tweetService;

    private final UserRepository userRepository;

    public ProfileController(AuthService authService, TweetService tweetService, UserRepository userRepository) {
        this.authService = authService;
        this.tweetService = tweetService;
        this.userRepository = userRepository;
    }

    @PostMapping("/profile")
    public String profile(Principal principal, Model model) {

        String username = principal.getName();
        UserEntity user = getUser(username);

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

    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
