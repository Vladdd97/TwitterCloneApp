package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TwittFollow;
import org.springframework.data.repository.CrudRepository;

public interface TwittFollowRepository extends CrudRepository<TwittFollow,Long> {

    //Iterable<TwittFollow> findAllByFollowingUsernameOrFollowerUsername (String following,String follower);

    Iterable<TwittFollow> findAllByFollowingUsername (String username);

    Iterable<TwittFollow> findAllByFollowerUsername (String username);

}
