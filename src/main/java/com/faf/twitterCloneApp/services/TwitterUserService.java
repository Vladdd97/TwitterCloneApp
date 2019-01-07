package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;

public interface TwitterUserService {

    Iterable<TwitterUser> getAll();

    TwitterUser findById (Long id);

    TwitterUser save(TwitterUser twitterUser);

    void deleteById(Long id);

    Long count();
}
