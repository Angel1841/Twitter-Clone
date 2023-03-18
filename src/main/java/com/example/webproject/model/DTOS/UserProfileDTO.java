package com.example.webproject.model.DTOS;

import com.example.webproject.model.entities.Comment;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserRoleEntity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfileDTO {

    private String username;

    private String email;

    private List<UserRoleEntity> role;

    private Set<Comment> comments;

    private Set<Tweet> tweets;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String username, String email, List<UserRoleEntity> role, Set<Comment> comments, Set<Tweet> tweets) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.comments = new HashSet<>();
        this.tweets = new HashSet<>();
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

    public List<UserRoleEntity> getRole() {
        return role;
    }

    public UserProfileDTO setRole(List<UserRoleEntity> role) {
        this.role = role;
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public UserProfileDTO setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public UserProfileDTO setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }
}
