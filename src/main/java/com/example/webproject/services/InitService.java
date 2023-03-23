package com.example.webproject.services;

import com.example.webproject.model.entities.*;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void init() {
        initRoles();
        initAdmin();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            userRoleRepository.save(userRole);
            userRoleRepository.save(adminRole);
        }
    }


    private void initAdmin(){
        var adminUser = new UserEntity().
                setEmail("admin@example.com").
                setUsername("adminkata").
                setPassword(passwordEncoder.encode("asdasd")).
                setRoles(userRoleRepository.findAll()).
                setLikes(new HashSet<Like>()).
                setTweets(new HashSet<Tweet>()).
                setRetweets(new HashSet<Retweet>());


        userRepository.save(adminUser);
    }


}
