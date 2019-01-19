package com.faf.twitterCloneApp.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
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
    @JsonManagedReference
    private List<Tweet> tweets;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    @JsonManagedReference
    private List<Authority> authorities;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "following")
    @JsonManagedReference
    private List<TweetFollow> followings;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "follower")
    @JsonManagedReference
    private List<TweetFollow> followers;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    @JsonManagedReference
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    @JsonManagedReference
    private TwitterUserInfo twitterUserInfo;

    public TwitterUser(){}

}
