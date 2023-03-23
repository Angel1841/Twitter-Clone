package com.example.webproject.model.DTOS;

import com.example.webproject.model.entities.Tweet;
import com.example.webproject.model.entities.UserEntity;
import lombok.*;

import java.text.DateFormat;
import java.time.Instant;

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

}
