package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.TwitterUserInfo;
import org.springframework.data.repository.CrudRepository;

public interface TwitterUserInfoRepository extends CrudRepository<TwitterUserInfo, Long> {
}
