package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwittFollow;
import com.faf.twitterCloneApp.repositories.TwittFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwittFollowServiceImpl implements TwittFollowService{

    @Autowired
    TwittFollowRepository twittFollowRepository;

    @Override
    public Iterable<TwittFollow> getAll() {
        return twittFollowRepository.findAll();
    }

    @Override
    public TwittFollow findById(Long id) {
        return twittFollowRepository.findById(id).get();
    }

    @Override
    public TwittFollow save(TwittFollow twittFollow) {
        return twittFollowRepository.save(twittFollow);
    }

    @Override
    public void deleteById(Long id) {
        twittFollowRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return twittFollowRepository.count();
    }

//    @Override
//    public Iterable<TwittFollow> findAllByFollowingUsernameOrFollowerUsername(String following,String follower) {
//        return twittFollowRepository.findAllByFollowingUsernameOrFollowerUsername(following,follower);
//    }


    @Override
    public Iterable<TwittFollow> findAllByFollowingUsername(String username) {
        return twittFollowRepository.findAllByFollowingUsername(username);
    }

    @Override
    public Iterable<TwittFollow> findAllByFollowerUsername(String username) {
        return twittFollowRepository.findAllByFollowerUsername(username);
    }
}
