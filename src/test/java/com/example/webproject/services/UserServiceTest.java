package com.example.webproject.services;

import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.example.webproject.model.DTOS.UserRegistrationDTO;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRoleRepository userRoleRepository;

    private UserService toTest;

    @BeforeEach
    void setUp() {
        toTest = new UserService(
                mockUserRepository,
                mockPasswordEncoder,
                userDetailsService,
                userRoleRepository);
    }

    @Test
    void testUserRegistration(){

        UserRegistrationDTO testRegistrationDTO = new UserRegistrationDTO().
                setEmail("test@example.com").
                setUsername("test4o").
                setPassword("password").
                setConfirmPassword("password");


        toTest.registerUser(testRegistrationDTO);

        verify(mockUserRepository).save(any());

    }







}
