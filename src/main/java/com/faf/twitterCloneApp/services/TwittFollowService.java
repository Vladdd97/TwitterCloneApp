package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwittFollow;

public interface TwittFollowService {

    Iterable<TwittFollow> getAll();

    TwittFollow findById (Long id);

    TwittFollow save(TwittFollow twittFollow);

    void deleteById(Long id);

    Long count();

    //Iterable<TwittFollow> findAllByFollowingUsernameOrFollowerUsername (String following,String follower);


    Iterable<TwittFollow> findAllByFollowingUsername (String username);

    Iterable<TwittFollow> findAllByFollowerUsername (String username);


}
