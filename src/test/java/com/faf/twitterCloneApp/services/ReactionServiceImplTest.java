package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.repositories.ReactionRepository;
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

public class ReactionServiceImplTest {

    @Mock
    private ReactionRepository reactionRepository;

    private ReactionServiceImpl reactionServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        reactionServiceImpl = new ReactionServiceImpl(reactionRepository);
    }

    @Test
    public void findAll() {

        Iterable<Reaction> reactions = new ArrayList<>();
        Reaction reaction1 = new Reaction();
        reaction1.setId(3L);
        reaction1.setLiked(true);
        reaction1.setTweet(null);
        reaction1.setLikedByUser(null);

        Reaction reaction2 = new Reaction();
        reaction2.setId(4L);
        reaction2.setLiked(true);
        reaction2.setTweet(null);
        reaction2.setLikedByUser(null);

        ((ArrayList<Reaction>) reactions).add(reaction1);
        ((ArrayList<Reaction>) reactions).add(reaction2);

        when(reactionRepository.findAll()).thenReturn(reactions);
        assertEquals(reactionServiceImpl.findAll(),reactions);
        verify(reactionRepository,times(1)).findAll();
    }

    @Test
    public void findById() {
        Long reactionId = 2L;
        Reaction reaction1 = new Reaction();
        reaction1.setId(reactionId);
        reaction1.setLiked(true);
        reaction1.setTweet(null);
        reaction1.setLikedByUser(null);

        Optional<Reaction> optionalReaction = Optional.of(reaction1);

        when(reactionRepository.findById(reactionId)).thenReturn(optionalReaction);
        assertEquals(reactionServiceImpl.findById(reactionId),optionalReaction.get());
        verify(reactionRepository,times(1)).findById(reactionId);

    }

    @Test
    public void save() {

        Reaction reaction1 = new Reaction();
        reaction1.setId(5L);
        reaction1.setLiked(true);
        reaction1.setTweet(null);
        reaction1.setLikedByUser(null);


        when(reactionRepository.save(reaction1)).thenReturn(reaction1);
        assertEquals(reactionRepository.save(reaction1),reaction1);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
        Long numberOfReactions = 5L;
        when(reactionRepository.count()).thenReturn(numberOfReactions);
        assertEquals(reactionServiceImpl.count(),numberOfReactions);
    }

    @Test
    public void findByTweetIdAndLikedByUser() {
    }
}