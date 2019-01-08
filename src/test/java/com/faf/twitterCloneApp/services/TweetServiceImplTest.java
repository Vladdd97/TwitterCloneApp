package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.repositories.TweetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class TweetServiceImplTest {

    @Mock
    private TweetRepository tweetRepository;

    private  TweetServiceImpl tweetServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tweetServiceImpl = new TweetServiceImpl(tweetRepository);
    }

    @Test
    public void findAll() {
        Iterable<Tweet> tweets = new ArrayList<>();
        Tweet tweet1 = new Tweet(2L,"content for tweet1",null,new Date(),null,null);
        Tweet tweet2 = new Tweet(3L,"content for tweet2",null,new Date(),null,null);

        ((ArrayList<Tweet>) tweets).add(tweet1);
        ((ArrayList<Tweet>) tweets).add(tweet2);

        Mockito.when(tweetRepository.findAll()).thenReturn(tweets);
        assertEquals(tweetServiceImpl.findAll(),tweets);
        Mockito.verify(tweetRepository,Mockito.times(1)).findAll();

    }

    @Test
    public void findById() {
        Long tweetId = 3L;
        Tweet tweet1 = new Tweet(2L,"content for tweet1",null,new Date(),null,null);
        Optional<Tweet> optionalTweet = Optional.of(tweet1);

        Mockito.when(tweetRepository.findById(tweetId)).thenReturn(optionalTweet);
        assertEquals(tweetServiceImpl.findById(tweetId),optionalTweet.get());

    }

    @Test
    public void save() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
    }

    @Test
    public void findAllByTwitterUserUsername() {
    }

    @Test
    public void findAllByTwitterUserUsernameOrderByCreateDateDesc() {
    }

    @Test
    public void findTopTweetsByNumberOfReactions() {
    }
}