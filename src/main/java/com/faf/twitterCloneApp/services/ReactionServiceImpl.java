package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService{

    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Iterable<Reaction> getAll() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction findById(Long id) {
        return reactionRepository.findById(id).get();
    }

    @Override
    public Reaction save(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    @Override
    public void deleteById(Long id) {
        reactionRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return reactionRepository.count();
    }

    @Override
    public Optional<Reaction> findByTweetIdAndLikedByUser(Long tweetId, String username) {
        return reactionRepository.findByTweetIdAndLikedByUser(tweetId,username);
    }
}
