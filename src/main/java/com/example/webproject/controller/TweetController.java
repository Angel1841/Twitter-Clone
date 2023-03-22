package com.example.webproject.controller;

import com.example.webproject.model.DTOS.TweetDTO;
import com.example.webproject.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createTweet(@RequestBody TweetDTO tweetDto, Principal principal){
        this.tweetService.tweet(tweetDto, principal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTweet(@PathVariable(name = "id") Long id){
        this.tweetService.deleteTweet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets-by-username/{username}")
    public ResponseEntity<List<TweetDTO>> getTweetsByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getTweetsByUsername(username));
    }

    @GetMapping("/retweets-by-username/{username}")
    public ResponseEntity<List<TweetDTO>> getRetweetsByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getRetweetsByUsername(username));
    }


    @GetMapping("/liked-by-username/{username}")
    public ResponseEntity<List<TweetDTO>> getLikedByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getLikedByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<TweetDTO>> getAll(){
        List<TweetDTO> tweets = this.tweetService.getAll();
        return ResponseEntity.ok(tweets);
    }


}
