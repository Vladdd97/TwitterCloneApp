package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Twitt;
import org.springframework.data.repository.CrudRepository;

public interface TwittRepository extends CrudRepository<Twitt,Long> {
}
