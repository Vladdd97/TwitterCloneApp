package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TweetFollow;

public interface TweetFollowService {

    Iterable<TweetFollow> findAll();

    TweetFollow findById (Long id);

    TweetFollow save(TweetFollow tweetFollow);

    void deleteById(Long id);

    Long count();

    //Iterable<TweetFollow> findAllByFollowingUsernameOrFollowerUsername (String following,String follower);


    Iterable<TweetFollow> findAllByFollowingUsername (String username);

    Iterable<TweetFollow> findAllByFollowerUsername (String username);


}
