package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TwitterUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TwitterUserRepository extends CrudRepository<TwitterUser,Long> {


    //Optional<TwitterUser> findByUsername(String username);

    //Optional<TwitterUser> findByUsername (String username);
}
