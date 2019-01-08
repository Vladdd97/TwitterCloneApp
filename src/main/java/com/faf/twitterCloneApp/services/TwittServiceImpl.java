package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.repositories.TwittRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwittServiceImpl implements TwittService {

    @Autowired
    private TwittRepository twittRepository;

    @Override
    public Iterable<Twitt> getAll() {
        return twittRepository.findAll();
    }

    @Override
    public Twitt findById(Long id) {
        return twittRepository.findById(id).get();
    }

    @Override
    public Twitt save(Twitt twitt) {
        return twittRepository.save(twitt);
    }

    @Override
    public void deleteById(Long id) {
        twittRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return twittRepository.count();
    }

    @Override
    public Iterable<Twitt> findAllByTwitterUserUsername(String username) {
        return twittRepository.findAllByTwitterUserUsername(username);
    }
}
