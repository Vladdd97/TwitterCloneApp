package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TweetService {

    Iterable<Tweet> findAll();

    Tweet findById (Long id);

    Tweet save(Tweet tweet);

    void deleteById(Long id);

    Long count();

    Iterable<Tweet> findAllByTwitterUserUsername(String username);

    List<Tweet> findTopTweetsByNumberOfReactions(Integer pageNumber, Integer pageSize);

}
