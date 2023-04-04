package com.example.webproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    private final String NOT_EXISTING_USERNAME = "randomUsername";

    private ApplicationUsersDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUsersDetailsService(
                mockUserRepository
        );
    }


    @Test
    void testUserFound() {

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        UserEntity testUserEntity = new UserEntity().
                setUsername("kiwi").
                setEmail("admin@example.com").
                setPassword("topsecret").
                setRoles(List.of(testAdminRole, testUserRole));


        when(mockUserRepository.findUserEntityByUsername("kiwi")).
                thenReturn(Optional.of(testUserEntity));

        UserDetails adminDetails = toTest.loadUserByUsername("kiwi");

        // assert methods
        Assertions.assertNotNull(adminDetails);

        Assertions.assertEquals("kiwi", adminDetails.getUsername());

        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "authorities have to be at 2");


    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }


}
