package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Tweet;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet,Long> {

    //findByTwitterUserUsername
    Iterable<Tweet> findAllByTwitterUserUsername(String username);
}
