package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.util.TweetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetViewServiceImpl implements TweetViewService{

    @Autowired
    TweetService tweetServiceImpl;

    @Override
    public List<TweetView> findAllTweetViewsByOrderByCreateDateDesc(Integer pageNumber, Integer pageSize) {
        List<TweetView> tweetViews = new ArrayList<>();
        Iterable<Tweet> tweets = tweetServiceImpl.findAllByOrderByCreateDateDesc(pageNumber, pageSize);
        tweets.forEach(t -> {
            Tweet parentTweet = null;
            Long parentTweetId = t.getParentTweetId();
            if (parentTweetId != null) {
                parentTweet = tweetServiceImpl.findById(parentTweetId);
            }
            tweetViews.add(new TweetView(parentTweet, t));
        });

        return tweetViews;
    }


    @Override
    public List<TweetView> findAllTweetViewsByTwitterUserUsername(String username) {
        ArrayList<TweetView> tweetViews = new ArrayList<>();
        Iterable<Tweet> tweets = tweetServiceImpl.findAllByTwitterUserUsernameOrderByCreateDateDesc(username);
        tweets.forEach(t -> {
            Tweet parentTweet = null;
            Long parentTweetId = t.getParentTweetId();
            if (parentTweetId != null) {
                parentTweet = tweetServiceImpl.findById(parentTweetId);
            }
            tweetViews.add(new TweetView(parentTweet, t));
        });

        return tweetViews;
    }

}
