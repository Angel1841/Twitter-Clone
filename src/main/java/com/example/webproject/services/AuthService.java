package com.example.webproject.services;

import com.example.webproject.util.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final LoggedUser userSession;

    public AuthService(LoggedUser userSession) {
        this.userSession = userSession;
    }

    public boolean isLogged(){
        return this.userSession.isLogged();
    }

}
