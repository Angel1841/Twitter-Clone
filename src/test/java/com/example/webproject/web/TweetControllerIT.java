package com.example.webproject.web;

import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.entities.UserRoleEntity;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.UserRepository;
import com.example.webproject.wrb.TweetController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TweetController tweetController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
    }

    @Test
    @WithMockUser(username = "adminkata", roles = {"USER",  "ADMIN"})
    public void testGetGroupsDelete() throws Exception {

        mockMvc.perform(get("/liked"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/liked"));
    }

}
