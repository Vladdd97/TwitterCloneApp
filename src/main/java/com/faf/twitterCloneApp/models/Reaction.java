package com.faf.twitterCloneApp.models;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;


@Entity
public class Reaction {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean liked;

    private String likedByUser;

    @ManyToOne
    private Twitt twitt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(String likedByUser) {
        this.likedByUser = likedByUser;
    }

    public Twitt getTwitt() {
        return twitt;
    }

    public void setTwitt(Twitt twitt) {
        this.twitt = twitt;
    }
}
