package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TweetFollow;
import com.faf.twitterCloneApp.repositories.TweetFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetFollowServiceImpl implements TweetFollowService {

    @Autowired
    TweetFollowRepository tweetFollowRepository;

    @Override
    public Iterable<TweetFollow> findAll() {
        return tweetFollowRepository.findAll();
    }

    @Override
    public TweetFollow findById(Long id) {
        return tweetFollowRepository.findById(id).get();
    }

    @Override
    public TweetFollow save(TweetFollow tweetFollow) {
        return tweetFollowRepository.save(tweetFollow);
    }

    @Override
    public void deleteById(Long id) {
        tweetFollowRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return tweetFollowRepository.count();
    }

    @Override
    public TweetFollow findByFollowingIdAndFollowerId(Long followingId, Long followerId) {
        return tweetFollowRepository.findByFollowingIdAndFollowerId(followingId,followerId);
    }

    //    @Override
//    public Iterable<TweetFollow> findAllByFollowingUsernameOrFollowerUsername(String following,String follower) {
//        return tweetFollowRepository.findAllByFollowingUsernameOrFollowerUsername(following,follower);
//    }


    @Override
    public Iterable<TweetFollow> findAllByFollowingUsername(String username) {
        return tweetFollowRepository.findAllByFollowingUsername(username);
    }

    @Override
    public Iterable<TweetFollow> findAllByFollowerUsername(String username) {
        return tweetFollowRepository.findAllByFollowerUsername(username);
    }
}
