package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;

public interface TweetService {

    Iterable<Tweet> getAll();

    Tweet findById (Long id);

    Tweet save(Tweet tweet);

    void deleteById(Long id);

    Long count();

    Iterable<Tweet> findAllByTwitterUserUsername(String username);

}
