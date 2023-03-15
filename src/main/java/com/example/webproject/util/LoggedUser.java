package com.example.webproject.util;

import com.example.webproject.model.entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String username;

    public LoggedUser() {
    }

    public void login(UserEntity userEntity){
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
    }

    public void logout(){
        this.id = null;
        this.username = null;
    }

    public boolean isLogged(){
        return this.getId() != null;
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }
}
