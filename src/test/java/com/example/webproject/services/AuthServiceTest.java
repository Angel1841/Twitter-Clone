package com.example.webproject.services;


import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private LoggedUser mockUserSession;

    private AuthService toTest;


    @BeforeEach
    void setUp() {
        toTest = new AuthService(
                mockUserRepository,
                mockUserSession
        );



    }

    @Test
    void testGetUser() {

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        UserEntity testUserEntity = new UserEntity().
                setUsername("asdasd").
                setEmail("admin@example.com").
                setPassword("topsecret").
                setRoles(List.of(testAdminRole, testUserRole));

        when(mockUserRepository.findUserEntityByUsername("asdasd")).
                thenReturn(Optional.of(testUserEntity));

        Assertions.assertEquals(this.mockUserRepository.findUserEntityByUsername("asdasd"), Optional.of(toTest.getUser("asdasd")));

    }

}
