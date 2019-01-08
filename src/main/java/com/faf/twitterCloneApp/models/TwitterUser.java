package com.faf.twitterCloneApp.models;


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

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<TweetFollow> getFollowings() {
        return followings;
    }

    public void setFollowings(List<TweetFollow> followings) {
        this.followings = followings;
    }

    public List<TweetFollow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<TweetFollow> followers) {
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
