package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TwitterUser;
import org.springframework.data.repository.CrudRepository;

public interface TwitterUserRepository extends CrudRepository<TwitterUser,Long> {
}
