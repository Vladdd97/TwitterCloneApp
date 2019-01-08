package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TwitterUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TwitterUserRepository extends CrudRepository<TwitterUser,Long> {


    //Optional<TwitterUser> findByUsername(String username);

    Optional<TwitterUser> findByUsername (String username);


//    select twitter_user.* from twitter_user where twitter_user.id not in
//            (
//                    select following_id from tweet_follow where tweet_follow.follower_id = 128
//            )

//
//    @Query("SELECT t FROM TwitterUser as t where t.id NOT IN " +
//            "(SELECT  f.id FROM TwitterUser as ttt left join ttt.followings as f)")


    @Query("SELECT t FROM TwitterUser as t where t.id NOT IN " +
            "(SELECT  f.following.id FROM TweetFollow as f where f.follower.id = ?1) and t.id <> ?1")
    Page<TwitterUser> findSuggestFollowUsers(Long twitterUserId,Pageable pageable);

}
