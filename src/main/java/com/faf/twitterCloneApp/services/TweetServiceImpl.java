package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.util.TweetType;
import com.faf.twitterCloneApp.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

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
    public Iterable<Tweet> findAllByTwitterUserUsernameAndType(String username, TweetType tweetType) {
        return tweetRepository.findAllByTwitterUserUsernameAndType(username,tweetType);
    }

    @Override
    public Iterable<Tweet> findAllByTwitterUserUsernameOrderByCreateDateDesc(String username) {
        return tweetRepository.findAllByTwitterUserUsernameOrderByCreateDateDesc(username);
    }


    @Override
    public Optional<Tweet> findByParentTweetIdAndTypeAndTwitterUserUsername(Long parentTweetId, TweetType tweetType, String username) {
        return tweetRepository.findByParentTweetIdAndTypeAndTwitterUserUsername(parentTweetId,tweetType,username);
    }

    @Override
    public List<Tweet> findTopTweetsByNumberOfReactions(Integer pageNumber, Integer pageSize) {

        List<Tweet> tweets = new ArrayList<>();
        Page<Tweet> page = tweetRepository.findTopTweetsByNumberOfReactions(new PageRequest(pageNumber, pageSize));
        page.get().forEach(t -> tweets.add(t));
        return tweets;
    }
}
