package com.example.webproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tweets")
public class Tweet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private String text;

    private Integer retweetCounter;

    private Integer likeCounter;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "tweet", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Retweet> retweets = new HashSet<>();

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "tweet_id", referencedColumnName = "id")
    //private Tweet tweet;

    private Date createdDate;

}



