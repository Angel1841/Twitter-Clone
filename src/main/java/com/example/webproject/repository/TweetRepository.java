package com.example.webproject.repository;

import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import com.example.webproject.model.enums.TweetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Optional<List<Tweet>> findAllByUser(UserEntity user);

    Optional<List<Tweet>> findAllByType(TweetType type);

    Optional<List<Tweet>> findAllByUserAndType(UserEntity user, TweetType type);

}
