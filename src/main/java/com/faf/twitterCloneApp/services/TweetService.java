package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;

import java.util.List;

public interface TweetService {

    Iterable<Tweet> findAll();

    Tweet findById (Long id);

    Tweet save(Tweet tweet);

    void deleteById(Long id);

    Long count();

    Iterable<Tweet> findAllByTwitterUserUsername(String username);

    List<Object> findTopTweetsByNumberOfReactions();

}
