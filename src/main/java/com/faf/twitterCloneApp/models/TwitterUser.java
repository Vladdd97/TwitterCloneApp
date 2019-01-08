package com.faf.twitterCloneApp.models;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private List<Twitt> twitts;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private List<Authority> authorities;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "following")
    private List<TwittFollow> followings;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "follower")
    private List<TwittFollow> followers;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "twitterUser")
    private TwitterUserInfo twitterUserInfo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Twitt> getTwitts() {
        return twitts;
    }

    public void setTwitts(List<Twitt> twitts) {
        this.twitts = twitts;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<TwittFollow> getFollowings() {
        return followings;
    }

    public void setFollowings(List<TwittFollow> followings) {
        this.followings = followings;
    }

    public List<TwittFollow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<TwittFollow> followers) {
        this.followers = followers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public TwitterUserInfo getTwitterUserInfo() {
        return twitterUserInfo;
    }

    public void setTwitterUserInfo(TwitterUserInfo twitterUserInfo) {
        this.twitterUserInfo = twitterUserInfo;
    }
}
