package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Authority;

public interface AuthorityService {

    Iterable<Authority> findAll();

    Authority findById (Long id);

    Authority save(Authority authority);

    void deleteById(Long id);

    Long count();

}
