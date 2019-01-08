package com.faf.twitterCloneApp.models;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
public class Reaction {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean liked;

    private String likedByUser;

    @ManyToOne
    private Tweet tweet;

}
