package com.example.webproject.repository;

import com.example.webproject.model.entities.Retweet;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByUserAndTweet(UserEntity user, Tweet tweet);

    Optional<List<Retweet>> findAllByUser(UserEntity user);

}
