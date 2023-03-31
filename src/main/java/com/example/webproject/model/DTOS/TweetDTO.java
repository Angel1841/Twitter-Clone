package com.example.webproject.model.DTOS;

import com.example.webproject.model.entities.Like;
import com.example.webproject.model.entities.Retweet;
import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.text.DateFormat;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {

    private Long id;
    private UserEntity user;
    private String text;
    //private Tweet tweet;
    private Integer retweetCounter;
    private Integer likeCounter;
    private DateFormat createDate;

    private Set<Like> likes;

    private Set<Retweet> retweets;

}
