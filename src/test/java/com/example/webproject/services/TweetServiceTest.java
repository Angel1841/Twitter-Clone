package com.example.webproject.services;

import com.example.webproject.model.DTOS.TweetDTO;
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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {


    @Mock
    private UserRepository mockUserRepository;

    @Captor
    private ArgumentCaptor<UserEntity> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Tweet> tweetArgumentCaptor;

    @Captor
    private ArgumentCaptor<Like> likeArgumentCaptor;

    @InjectMocks
    private AuthService authService;

    @Mock
    private TweetRepository mockTweetRepository;

    @Mock
    private LikeRepository mockLikeRepository;

    @Mock
    private RetweetRepository mockRetweetRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TweetService toTest;

    @Mock
    private Principal principal;

    private UserEntity testUserEntity;

    private Tweet testTweet;
    private Like testLike;

    private Retweet testRetweet;


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

        testUserEntity = new UserEntity().
                setId(1L).
                setUsername("asdasd").
                setEmail("asd@asd").
                setPassword("asdasd").
                setRoles(List.of(testUserRole));


        testTweet = Tweet.builder()
                .id(1L)
                .user(testUserEntity)
                .text("hello")
                .retweetCounter(0)
                .likeCounter(0)
                .createdDate(Date.from(Instant.now()))
                .build();

        testLike = Like.builder()
                .id(1L)
                .tweet(testTweet)
                .user(testUserEntity)
                .build();

        testRetweet = Retweet.builder()
                .id(1L)
                .tweet(testTweet)
                .user(testUserEntity)
                .build();

    }

    @Test
    void createTweetTest() { //fix

        when(this.principal.getName()).thenReturn(testUserEntity.getUsername());

        when(this.mockUserRepository.findUserEntityByUsername("asdasd")).thenReturn(Optional.of(testUserEntity));

        toTest.tweet("hello", principal);

        Mockito.verify(mockTweetRepository).save(tweetArgumentCaptor.capture());


    }




    @Test
    void deleteTweet() {

        when(this.mockTweetRepository.findById(testTweet.getId())).thenReturn(Optional.of(testTweet));

        toTest.deleteTweet(testTweet.getId());

        Mockito.verify(mockTweetRepository).delete(tweetArgumentCaptor.capture());

    }

    @Test
    void getRetweetsByUsername(){

        when(this.principal.getName()).thenReturn(testUserEntity.getUsername());

        when(this.mockUserRepository.findUserEntityByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

        when(mockTweetRepository.findById(testTweet.getId())).thenReturn(Optional.of(testTweet));

        when(mockRetweetRepository.findByUserAndTweet(testUserEntity, testTweet)).thenReturn(Optional.of(testRetweet));

        when(this.mockRetweetRepository.findAllByUser(testUserEntity)).thenReturn(Optional.of(Optional.of(testRetweet).stream().toList()));

        when(this.mockTweetRepository.findById(testTweet.getId())).thenReturn(Optional.of(testTweet));

        toTest.retweet(testTweet.getId(), principal);

        Assertions.assertEquals(1, toTest.getRetweetsByUsername(testUserEntity.getUsername()).size());

    }

    @Test
    void getTweetsByUsernameTest() {

        when(this.principal.getName()).thenReturn(testUserEntity.getUsername());

        when(this.mockUserRepository.findUserEntityByUsername("asdasd")).thenReturn(Optional.of(testUserEntity));

        when(this.mockTweetRepository.findAllByUser(testUserEntity)).thenReturn(Optional.of(Optional.of(testTweet).stream().toList()));;

        toTest.tweet("hello", principal);

        Mockito.verify(mockTweetRepository).save(tweetArgumentCaptor.capture());

        Assertions.assertEquals(1, toTest.getTweetsByUsername(testUserEntity.getUsername()).size());
    }

    @Test
    void getLikedByUsernameTest(){

        when(this.principal.getName()).thenReturn(testUserEntity.getUsername());

        when(this.mockUserRepository.findUserEntityByUsername(testUserEntity.getUsername())).thenReturn(Optional.of(testUserEntity));

        when(mockTweetRepository.findById(testTweet.getId())).thenReturn(Optional.of(testTweet));

        when(mockLikeRepository.findByUserAndTweet(testUserEntity, testTweet)).thenReturn(Optional.of(testLike));

        when(this.mockLikeRepository.findAllByUser(testUserEntity)).thenReturn(Optional.of(Optional.of(testLike).stream().toList()));

        when(this.mockTweetRepository.findById(testTweet.getId())).thenReturn(Optional.of(testTweet));

        toTest.like(testTweet.getId(), principal);

        Assertions.assertEquals(1, toTest.getLikedByUsername(testUserEntity.getUsername()).size());

    }
}
