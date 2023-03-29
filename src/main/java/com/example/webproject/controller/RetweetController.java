package com.example.webproject.controller;

import com.example.webproject.model.DTOS.LikeRetweetDTO;
import com.example.webproject.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class RetweetController {

    private final TweetService tweetService;

    //@PostMapping("/retweet")
    //public ResponseEntity<HttpStatus> postRetweet(@RequestBody LikeRetweetDTO likeRetweetDto, Principal principal){
    //    this.tweetService.retweet(likeRetweetDto, principal);
    //    return ResponseEntity.ok().build();
    //}

    @PostMapping("/is-retweeted")
    public ResponseEntity<Boolean> isRetweeted(@RequestBody LikeRetweetDTO likeRetweetDto, Principal principal){
        return ResponseEntity.ok(this.tweetService.isRetweeted(likeRetweetDto, principal));
    }

}
