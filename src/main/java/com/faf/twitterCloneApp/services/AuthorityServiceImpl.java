package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Authority;
import com.faf.twitterCloneApp.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorityServiceImpl implements AuthorityService {


    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Iterable<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).get();
    }

    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return authorityRepository.count();
    }
}
