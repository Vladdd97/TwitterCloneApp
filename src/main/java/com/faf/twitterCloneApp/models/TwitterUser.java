package com.faf.twitterCloneApp.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class TwitterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private List<Tweet> tweets;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private List<Authority> authorities;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "following")
    private List<TweetFollow> followings;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "follower")
    private List<TweetFollow> followers;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private TwitterUserInfo twitterUserInfo;

    public TwitterUser(){}

}
