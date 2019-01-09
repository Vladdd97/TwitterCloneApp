package com.faf.twitterCloneApp.models;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
public class TweetFollow {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TwitterUser following;

    @ManyToOne
    private TwitterUser follower;

    public TweetFollow(){}

}
