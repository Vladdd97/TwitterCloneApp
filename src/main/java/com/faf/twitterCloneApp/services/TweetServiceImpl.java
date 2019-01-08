package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Iterable<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet findById(Long id) {
        return tweetRepository.findById(id).get();
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteById(Long id) {
        tweetRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return tweetRepository.count();
    }

    @Override
    public Iterable<Tweet> findAllByTwitterUserUsername(String username) {
        return tweetRepository.findAllByTwitterUserUsername(username);
    }

    @Override
    public List<Tweet> findTopTweetsByNumberOfReactions(Integer pageNumber, Integer pageSize) {

        List<Tweet> tweets = new ArrayList<>();
        Page<Tweet> page = tweetRepository.findTopTweetsByNumberOfReactions(new PageRequest(pageNumber, pageSize));
        page.get().forEach(t -> tweets.add(t));
        return tweets;
    }
}
