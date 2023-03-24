package com.example.webproject.model.DTOS;

import com.example.webproject.model.entities.UserEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRetweetDTO {
    private Long tweetId;

    private UserEntity user;

    private String text;

    private Integer retweetCounter;

    private Integer likeCounter;

    private Date createdDate;

}
