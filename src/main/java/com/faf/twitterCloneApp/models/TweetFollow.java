package com.faf.twitterCloneApp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private TwitterUser following;

    @ManyToOne
    @JsonBackReference
    private TwitterUser follower;

    public TweetFollow(){}

}
