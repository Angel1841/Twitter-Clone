package com.example.webproject.model.DTOS;

import com.example.webproject.model.entities.Like;
import com.example.webproject.model.entities.Retweet;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserRoleEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfileDTO {

    private String username;

    private String email;

    private List<UserRoleEntity> roles;

    private Set<Like> likes;

    private Set<Tweet> tweets;

    private Set<Retweet> retweets;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String username, String email, List<UserRoleEntity> roles, Set<Like> likes, Set<Tweet> tweets, Set<Retweet> retweets) {
        this.username = username;
        this.email = email;
        this.likes = likes;
        this.tweets = tweets;
        this.retweets = retweets;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserProfileDTO setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public UserProfileDTO setLikes(Set<Like> likes) {
        this.likes = likes;
        return this;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public UserProfileDTO setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public UserProfileDTO setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
        return this;
    }
}
