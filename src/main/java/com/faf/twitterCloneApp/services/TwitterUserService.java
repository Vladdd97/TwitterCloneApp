package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TwitterUserService {

    Iterable<TwitterUser> findAll();

    TwitterUser findById(Long id);

    TwitterUser save(TwitterUser twitterUser);

    Optional<TwitterUser> findByUsername(String username);

    void deleteById(Long id);

    Long count();

    List<TwitterUser> findSuggestFollowUsers(Long twitterUserId, Integer pageNumber, Integer pageSize);
}
