package com.example.webproject.services;

import com.example.webproject.model.entities.*;
import com.example.webproject.model.enums.UserRoleEnum;
import com.example.webproject.repository.LikeRepository;
import com.example.webproject.repository.RetweetRepository;
import com.example.webproject.repository.TweetRepository;
import com.example.webproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {


    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private AuthService authService;

    @Mock
    private TweetRepository mockTweetRepository;

    @Mock
    private LikeRepository mockLikeRepository;

    @Mock
    private RetweetRepository mockRetweetRepository;

    @Mock
    private ModelMapper modelMapper;

    private TweetService toTest;

    @Mock
    private Principal principal;

    private UserEntity testUserEntity;


    @BeforeEach
    void setUp() {
        toTest = new TweetService(
                mockUserRepository,
                authService,
                mockTweetRepository,
                mockLikeRepository,
                mockRetweetRepository,
                modelMapper
        );

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        tweets = new HashSet<>();

        tweets.add(Tweet.builder()
                .user(testUserEntity)
                .text("hello")
                .retweetCounter(0)
                .likeCounter(0)
                .createdDate(Date.from(Instant.now()))
                .build());

        testUserEntity = new UserEntity().
                setId(1L).
                setUsername("asdasd").
                setEmail("asd@asd").
                setPassword("asdasd").
                setRoles(List.of(testUserRole));



    }

    @Test
    void createTweetTest() { //fix


        toTest.tweet("hello", principal);


        Assertions.assertEquals(1, this.mockTweetRepository.findAllByUser(testUserEntity).stream().count());

    }

    @Test
    void getAllTweetsTest() {

        this.mockTweetRepository.save(Tweet.builder()
                .user(testUserEntity)
                .text("text")
                .retweetCounter(0)
                .likeCounter(0)
                .createdDate(Date.from(Instant.now()))
                .build());



        Assertions.assertEquals(mockTweetRepository.findAll().size(), toTest.getAllTweets().size());

    }

    @Test
    void likeTweetTest() {

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        UserEntity testUserEntity = new UserEntity().
                setId(1L).
                setUsername("adminkata").
                setEmail("admin@example.com").
                setPassword("asdasd").
                setRoles(List.of(testAdminRole, testUserRole));

        List<Tweet> tweets = new ArrayList<>();

        tweets.add(0, Tweet.builder()
                        .id(1L)
                .user(testUserEntity)
                .text("hello")
                .retweetCounter(0)
                .likeCounter(0)
                .createdDate(Date.from(Instant.now()))
                .build()
        );

        Tweet tweet = tweets.get(0);

        toTest.like(tweet.getId(), principal);



    }




}
