package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUserInfo;

public interface TwitterUserInfoService {

    Iterable<TwitterUserInfo> findAll();

    TwitterUserInfo findById (Long id);

    TwitterUserInfo save(TwitterUserInfo twitterUserInfo);

    void deleteById(Long id);

    Long count();
}
