package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Twitt;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TwittRepository extends CrudRepository<Twitt,Long> {

    //findByTwitterUserUsername
    Iterable<Twitt> findAllByTwitterUserUsername(String username);
}
