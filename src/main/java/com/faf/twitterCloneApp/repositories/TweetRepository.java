package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends CrudRepository<Tweet,Long> {

    //findByTwitterUserUsername
    Iterable<Tweet> findAllByTwitterUserUsername(String username);

    @Query("SELECT t FROM Tweet as t left join t.reactions as r group by t order by count(r.id) desc ")
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
    List<Object> findTopTweetsByNumberOfReactions();
}
