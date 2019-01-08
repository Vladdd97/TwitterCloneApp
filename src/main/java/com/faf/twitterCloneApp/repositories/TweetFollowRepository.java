package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TweetFollow;
import org.springframework.data.repository.CrudRepository;

public interface TweetFollowRepository extends CrudRepository<TweetFollow,Long> {

    //Iterable<TweetFollow> findAllByFollowingUsernameOrFollowerUsername (String following,String follower);

    Iterable<TweetFollow> findAllByFollowingUsername (String username);

    Iterable<TweetFollow> findAllByFollowerUsername (String username);

    //TweetFollow findByFollowingIdAndFollowerId(Long followingId, Long followerId);

}
