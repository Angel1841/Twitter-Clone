package com.example.webproject.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Tweet> tweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Retweet> retweets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )

    private List<UserRoleEntity> roles; //–  user's role (UserEntity or Admin).

    public UserEntity() {
    }

    public UserEntity(String username, String password, String email, Set<Like> likes, Set<Tweet> tweets, Set<Retweet> retweets, List<UserRoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.likes = new HashSet<>();
        this.tweets = new HashSet<>();
        this.retweets = new HashSet<>();
        this.roles = new ArrayList<>();
    }

    public boolean isAdmin(){
        for (var role: this.roles){

            String roleName = role.getRole().toString();

            if(roleName.equals("ADMIN")){
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public UserEntity setLikes(Set<Like> likes) {
        this.likes = likes;
        return this;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public UserEntity setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public UserEntity setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
