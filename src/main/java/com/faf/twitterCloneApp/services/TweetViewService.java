package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.util.TweetView;

import java.util.List;

public interface TweetViewService {

    List<TweetView>  findAllTweetViewsByOrderByCreateDateDesc (Integer pageNumber, Integer pageSize);

    List<TweetView> findAllTweetViewsByTwitterUserUsername(String username);

}
