package com.example.webproject.model.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> userLikes;

    public Post() {
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public User getCreator() {
        return creator;
    }

    public Post setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public Set<User> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }
}
