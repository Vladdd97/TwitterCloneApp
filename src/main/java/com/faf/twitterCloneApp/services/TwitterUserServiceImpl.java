package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterUserServiceImpl implements TwitterUserService {

    @Autowired
    TwitterUserRepository twitterUserRepository;

    @Override
    public Iterable<TwitterUser> findAll() {
        return twitterUserRepository.findAll();
    }

    @Override
    public TwitterUser findById(Long id) {
        return twitterUserRepository.findById(id).get();
    }

    @Override
    public TwitterUser save(TwitterUser twitterUser) {
        return twitterUserRepository.save(twitterUser);
    }

    @Override
    public void deleteById(Long id) {
        twitterUserRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return twitterUserRepository.count();
    }

    @Override
    public Optional<TwitterUser> findByUsername(String username) {
        return twitterUserRepository.findByUsername(username);
    }
}
