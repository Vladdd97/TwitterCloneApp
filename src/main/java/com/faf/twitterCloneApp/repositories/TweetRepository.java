package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends CrudRepository<Tweet,Long> {

    //findByTwitterUserUsername
    Iterable<Tweet> findAllByTwitterUserUsername(String username);

    Iterable<Tweet> findAllByTwitterUserUsernameOrderByCreateDateDesc(String username);

    Optional<Tweet> findByParentTweetId(Long parentTweetId);

    @Query("SELECT t FROM Tweet as t left join t.reactions as r group by t order by count(r.id) desc")
//    @Query(value = "" +
//            "SELECT tweet.id \n" +
//            "FROM   tweet \n" +
//            "       LEFT JOIN reaction \n" +
//            "              ON tweet.id = reaction.tweet_id \n" +
//            "       INNER JOIN twitter_user \n" +
//            "               ON tweet.twitter_user_id = twitter_user.id \n" +
//            "GROUP  BY tweet.id \n" +
//            "ORDER  BY Count(reaction.liked) DESC \n" +
//            "OFFSET  ?1 " +
//            "LIMIT  ?2 " +
//            "",
//            nativeQuery = true)
    Page<Tweet>  findTopTweetsByNumberOfReactions(Pageable pageable);
}
