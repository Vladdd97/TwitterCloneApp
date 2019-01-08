package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUserInfo;
import com.faf.twitterCloneApp.repositories.TwitterUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterUserInfoServiceImpl implements TwitterUserInfoService {

    private TwitterUserInfoRepository twitterUserInfoRepository;

    @Autowired
    public TwitterUserInfoServiceImpl(TwitterUserInfoRepository twitterUserInfoRepository) {
        this.twitterUserInfoRepository = twitterUserInfoRepository;
    }

    @Override
    public Iterable<TwitterUserInfo> findAll() {
        return twitterUserInfoRepository.findAll();
    }

    @Override
    public TwitterUserInfo findById(Long id) {
        return twitterUserInfoRepository.findById(id).get();
    }

    @Override
    public TwitterUserInfo save(TwitterUserInfo twitterUserInfo) {
        return twitterUserInfoRepository.save(twitterUserInfo);
    }

    @Override
    public void deleteById(Long id) {
        twitterUserInfoRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return twitterUserInfoRepository.count();
    }
}
