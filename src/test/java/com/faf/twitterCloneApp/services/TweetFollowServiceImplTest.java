package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TweetFollow;
import com.faf.twitterCloneApp.repositories.TweetFollowRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TweetFollowServiceImplTest {

    @Mock
    private TweetFollowRepository tweetFollowRepository;

    private TweetFollowServiceImpl tweetFollowServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tweetFollowServiceImpl = new TweetFollowServiceImpl(tweetFollowRepository);
    }

    @Test
    public void findAll() {
        Iterable<TweetFollow> tweetFollows = new ArrayList<>();
        TweetFollow tweetFollow1 = new TweetFollow(7L,null,null);
        TweetFollow tweetFollow2 = new TweetFollow(8L,null,null);

        ((ArrayList<TweetFollow>) tweetFollows).add(tweetFollow1);
        ((ArrayList<TweetFollow>) tweetFollows).add(tweetFollow2);

        when(tweetFollowRepository.findAll()).thenReturn(tweetFollows);
        assertEquals(tweetFollowServiceImpl.findAll(),tweetFollows);
        verify(tweetFollowRepository,times(1)).findAll();
    }

    @Test
    public void findById() {

        Long tweetFollowId = 2L;
        TweetFollow tweetFollow1 = new TweetFollow(7L,null,null);
        Optional<TweetFollow> optionalTweetFollow = Optional.of(tweetFollow1);

        when(tweetFollowRepository.findById(tweetFollowId)).thenReturn(optionalTweetFollow);
        assertEquals(tweetFollowServiceImpl.findById(tweetFollowId),optionalTweetFollow.get());
        verify(tweetFollowRepository,times(1)).findById(tweetFollowId);
    }

    @Test
    public void save() {
        TweetFollow tweetFollow1 = new TweetFollow(7L,null,null);

        when(tweetFollowRepository.save(tweetFollow1)).thenReturn(tweetFollow1);
        assertEquals(tweetFollowServiceImpl.save(tweetFollow1),tweetFollow1);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
        Long numberOfTweetFollows = 4L;
        when(tweetFollowRepository.count()).thenReturn(numberOfTweetFollows);
        assertEquals(tweetFollowServiceImpl.count(),numberOfTweetFollows);
    }

    @Test
    public void findByFollowingIdAndFollowerId() {
        Long followingId = 4L;
        Long followerId = 5L;
        TweetFollow tweetFollow1 = new TweetFollow(7L,null,null);

        when(tweetFollowRepository.findByFollowingIdAndFollowerId(followingId,followerId)).thenReturn(tweetFollow1);
        assertEquals(tweetFollowServiceImpl.findByFollowingIdAndFollowerId(followingId,followerId),tweetFollow1);

    }

    @Test
    public void findAllByFollowingUsername() {
    }

    @Test
    public void findAllByFollowerUsername() {
    }
}