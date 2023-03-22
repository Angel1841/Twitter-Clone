package com.example.webproject.repository;

import com.example.webproject.model.entities.Like;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndTweet(UserEntity user, Tweet tweet);
    Optional<List<Like>> findAllByUser(UserEntity user);

}
