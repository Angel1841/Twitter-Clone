package com.example.webproject.web;

import com.example.webproject.model.DTOS.UserRegistrationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TweetController tweetController;

    @Test
    public void testPostRegister() throws Exception {

        mockMvc.perform(post("/register").
                param("email", "pesho@example.com").
                param("username", "peshkata").
                param("password", "peshoto").
                param("confirmPassword", "peshoto").
                with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/login"));

    }

    @Test
    public void testPostLogin() throws Exception {

        mockMvc.perform(post("/login").
                        param("username", "peshkata").
                param("password", "peshoto").
                        with(csrf())).
                andExpect(status().is2xxSuccessful());

    }

    @Test
    public void errorRegisterTest() throws Exception {

        UserRegistrationDTO dto = new UserRegistrationDTO();

        dto.setUsername("a");
        dto.setEmail("a");
        dto.setPassword("a");
        dto.setConfirmPassword("a");


        mockMvc.perform(post("/register").
                        param("email", "a").
                        param("username", "a").
                        param("password", "a").
                        param("confirmPassword", "a").
                flashAttr("registrationDTO", dto).
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/register"));

    }

    @Test
    public void testGetRegister() throws Exception {

        mockMvc.perform(get("/register").
                        with(csrf())).
                andExpect(status().isOk());

    }



}
