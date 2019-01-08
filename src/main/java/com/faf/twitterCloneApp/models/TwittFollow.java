package com.faf.twitterCloneApp.models;


import javax.persistence.*;

@Entity
public class TwittFollow {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TwitterUser following;

    @ManyToOne
    private TwitterUser follower;

    public TwitterUser getFollowing() {
        return following;
    }

    public void setFollowing(TwitterUser following) {
        this.following = following;
    }

    public TwitterUser getFollower() {
        return follower;
    }

    public void setFollower(TwitterUser follower) {
        this.follower = follower;
    }
}
