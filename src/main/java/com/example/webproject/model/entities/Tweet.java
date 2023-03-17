package com.example.webproject.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tweets")
public class Tweet extends BaseEntity{

    @Column
    private String message;

    @ManyToOne
    private UserEntity user;


    public String getMessage() {
        return message;
    }

    public Tweet setMessage(String message) {
        this.message = message;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public Tweet setUser(UserEntity user) {
        this.user = user;
        return this;
    }

}
