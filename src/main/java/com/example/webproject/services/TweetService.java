package com.example.webproject.services;

import com.example.webproject.model.DTOS.LikeRetweetDTO;
import com.example.webproject.model.DTOS.TweetDTO;
import com.example.webproject.model.entities.Like;
import com.example.webproject.model.entities.Retweet;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.enums.TweetType;
import com.example.webproject.repository.LikeRepository;
import com.example.webproject.repository.RetweetRepository;
import com.example.webproject.repository.TweetRepository;
import com.example.webproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final UserRepository userRepository;
    private final AuthService authService;

    private final TweetRepository tweetRepository;

    private final LikeRepository likeRepository;

    private final RetweetRepository retweetRepository;

    private final ModelMapper modelMapper;



    @Transactional
    public void tweet(TweetDTO tweetDto, Principal principal) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        if(tweetDto.getId() != null){
            Tweet tweet = tweetRepository.findById(tweetDto.getId()).orElseThrow();

            this.tweetRepository.save(tweet);
        }

        else{
            this.createTweet(user, tweetDto.getText(), null);
        }

    }

    private void createTweet(UserEntity user, String text, Tweet tweet){
        this.tweetRepository.save(
                Tweet.builder()
                        .user(user)
                        .text(text)
                        .retweetCounter(0)
                        .likeCounter(0)
                        .tweet(tweet)
                        .createdDate(Instant.now())
                        .build()
        );
    }

    @Transactional
    public void deleteTweet(Long id){
        Tweet tweet = this.tweetRepository.findById(id).orElseThrow();
        this.tweetRepository.deleteById(tweet.getId());
    }

    @Transactional
    public void like(LikeRetweetDTO likeRetweetDto, Principal principal) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        Tweet tweet = this.tweetRepository.findById(likeRetweetDto.getTweetId()).orElseThrow();

        Optional<Like> optional = this.likeRepository.findByUserAndTweet(user, tweet);

        if(optional.isPresent()){
            tweet.setLikeCounter(tweet.getLikeCounter() - 1);
            this.tweetRepository.save(tweet);
            this.likeRepository.delete(optional.get());
        }
        else{
            tweet.setLikeCounter(tweet.getLikeCounter() + 1);
            this.tweetRepository.save(tweet);
            this.likeRepository.save(
                    Like.builder()
                            .user(user)
                            .tweet(tweet)
                            .build()
            );
        }
    }

    @Transactional
    public void retweet(LikeRetweetDTO likeRetweetBookmarkDto, Principal principal) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        Tweet tweet = this.tweetRepository.findById(likeRetweetBookmarkDto.getTweetId()).orElseThrow();
        Optional<Retweet> retweet = this.retweetRepository.findByUserAndTweet(user, tweet);

        if(retweet.isPresent()){
            tweet.setRetweetCounter(tweet.getRetweetCounter() - 1);
            this.retweetRepository.delete(retweet.get());
        }

        else{
            tweet.setRetweetCounter(tweet.getRetweetCounter() + 1);
            this.retweetRepository.save(
                    Retweet.builder()
                            .tweet(tweet)
                            .user(user)
                            .build()
            );
        }
        this.tweetRepository.save(tweet);
    }

    @Transactional(readOnly = true)
    public List<TweetDTO> getAllTweets() {
        return this.tweetRepository.findAll().stream().map(m -> this.modelMapper.map(m, TweetDTO.class)).collect(Collectors.toList());
    }


    @Transactional
    public TweetDTO getTweet(Long id) {
        return this.modelMapper.map(this.tweetRepository.findById(id).orElseThrow(), TweetDTO.class);
    }

    @Transactional(readOnly = true)
    public Boolean isLiked(LikeRetweetDTO likeRetweetDto, Principal principal) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        Tweet tweet = this.tweetRepository.findById(likeRetweetDto.getTweetId()).orElseThrow();
        return this.likeRepository.findByUserAndTweet(user, tweet).isPresent();
    }

    @Transactional
    public List<TweetDTO> getAll() {

        return this.tweetRepository.findAll().stream()
                .map(m -> this.modelMapper.map(m, TweetDTO.class))
                .collect(Collectors.toList());

    }

    @Transactional
    public List<TweetDTO> getTweetsByUsername(String username) {
        UserEntity user = this.userRepository.findUserEntityByUsername(username).orElseThrow();
        return this.tweetRepository.findAllByUser(user).orElseThrow().stream().map(m -> this.modelMapper.map(m, TweetDTO.class)).collect(Collectors.toList());

    }

    @Transactional
    public List<TweetDTO> getRetweetsByUsername(String username) {
        UserEntity user = this.userRepository.findUserEntityByUsername(username).orElseThrow();

        return this.retweetRepository.findAllByUser(user)
                .orElseThrow().stream().map(m -> this.modelMapper.map(m, TweetDTO.class)).collect(Collectors.toList());

    }

    @Transactional
    public List<TweetDTO> getLikedByUsername(String username) {
        UserEntity user = this.userRepository.findUserEntityByUsername(username).orElseThrow();
        List<Like> likes = this.likeRepository.findAllByUser(user).orElseThrow();

        return likes.stream().map(Like::getTweet).map(m -> this.modelMapper.map(m, TweetDTO.class)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Boolean isRetweeted(LikeRetweetDTO likeRetweetDto, Principal principal) {
        String username = principal.getName();
        UserEntity user = authService.getUser(username);

        Tweet tweet = this.tweetRepository.findById(likeRetweetDto.getTweetId()).orElseThrow();

        return this.retweetRepository.findByUserAndTweet(user, tweet).isPresent();
    }

}
