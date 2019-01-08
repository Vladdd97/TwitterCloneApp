package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<TwitterUser> findSuggestFollowUsers(Long twitterUserId, Integer pageNumber, Integer pageSize) {
        List<TwitterUser> users = new ArrayList<>();
        Page<TwitterUser> page = twitterUserRepository.findSuggestFollowUsers(twitterUserId, new PageRequest(pageNumber, pageSize));
        page.get().forEach(u -> users.add(u));
        return users;
    }
}
