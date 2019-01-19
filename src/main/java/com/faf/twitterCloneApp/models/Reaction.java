package com.faf.twitterCloneApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Reaction {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean liked;

    private String likedByUser;

    @ManyToOne
    @JsonBackReference
    private Tweet tweet;

    public Reaction(){}

}
