package com.example.webproject.wrb;

import com.example.webproject.model.DTOS.TweetDTO;
import com.example.webproject.model.DTOS.UserProfileDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InformationController {

    private final AuthService authService;

    private final TweetService tweetService;

    private final UserRepository userRepository;

    @GetMapping("/liked")
    public String likedTweets(Model model, Principal principal){

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
        model.addAttribute("likedByUser", tweetService.getLikedByUsername(user.getUsername()));

        return "/liked";
    }

    @GetMapping("/retweeted")
    public String retweetedTweets(Model model, Principal principal){

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
        model.addAttribute("retweetedByUser", tweetService.getRetweetsByUsername(user.getUsername()));

        return "/retweeted";
    }

    //@GetMapping("/retweets-by-username/{username}")
    //public List<LikeRetweetDTO> getRetweetsByUsername(@PathVariable(name = "username") String username){
    //    return this.tweetService.getRetweetsByUsername(username);
    //}


    //@GetMapping("/tweets-by-username/{username}")
    //public List<TweetDTO> getTweetsByUsername(@PathVariable(name = "username") String username){
    //    return this.tweetService.getTweetsByUsername(username);
    //}

    @GetMapping
    public List<TweetDTO> getAll(){

        return this.tweetService.getAll();
    }

    public UserEntity getUser(String username) {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }


}
