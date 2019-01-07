package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Twitt;

public interface TwittService {

    Iterable<Twitt> getAll();

    Twitt findById (Long id);

    Twitt save(Twitt twitt);

    void deleteById(Long id);

    Long count();

}
