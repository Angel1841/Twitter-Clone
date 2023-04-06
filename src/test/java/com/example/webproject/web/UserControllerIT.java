package com.example.webproject.web;



import com.example.webproject.model.DTOS.UserRegistrationDTO;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.UserService;
import com.example.webproject.wrb.AuthController;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@Transactional
//@ContextConfiguration(classes= AuthController.class)
@AutoConfigureMockMvc

public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Mock
    private SecurityContextRepository securityContextRepository;

    private ObjectMapper objectMapper;

    private UserRegistrationDTO user;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
    }


    @Test
    void testRegistration() throws Exception {


    }

}
