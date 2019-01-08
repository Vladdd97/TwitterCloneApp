package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;

import java.util.Optional;

public interface TwitterUserService {

    Iterable<TwitterUser> getAll();

    TwitterUser findById (Long id);

    TwitterUser save(TwitterUser twitterUser);

    Optional<TwitterUser> findByUsername (String username);

    void deleteById(Long id);

    Long count();
}
