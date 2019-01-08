package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Reaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReactionRepository extends CrudRepository<Reaction,Long> {

    Optional<Reaction> findByTweetIdAndLikedByUser (Long twittId , String username);
}
