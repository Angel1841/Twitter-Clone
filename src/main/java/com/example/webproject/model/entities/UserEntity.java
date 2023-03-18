package com.example.webproject.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username; // 3 symbols min

    @Column(nullable = false)
    private String password;  //4 symbols min

    @Column(nullable = false)
    private String email;


    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Tweet> tweets;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<UserRoleEntity> roles; //–  user's role (UserEntity or Admin).


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

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity addRole(UserRoleEntity role) {
        this.roles.add(role);
        return this;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public UserEntity setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public UserEntity setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }
}
