package com.example.webproject.web;



import com.example.webproject.model.DTOS.UserRegistrationDTO;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.services.AuthService;
import com.example.webproject.services.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


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
