package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Reaction;

import java.util.Optional;

public interface ReactionService {

    Iterable<Reaction> findAll();

    Reaction findById (Long id);

    Reaction save(Reaction reaction);

    void deleteById(Long id);

    Long count();

    Optional<Reaction> findByTweetIdAndLikedByUser (Long tweetId , String username);
}
