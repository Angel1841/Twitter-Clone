package com.example.webproject.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tweetid")
    private Tweet tweet;

    public Comment()
    {
    }

    public Comment(String content, UserEntity user)
    {
        this.content = content;
        this.user = user;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }


    public Tweet getTweet()
    {
        return tweet;
    }

    public void setTweet(Tweet tweet)
    {
        this.tweet = tweet;
    }

    public UserEntity getUser() {
        return user;
    }

    public Comment setUser(UserEntity user) {
        this.user = user;
        return this;
    }

}
