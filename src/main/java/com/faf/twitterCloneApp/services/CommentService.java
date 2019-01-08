package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Comment;

public interface CommentService {

    Iterable<Comment> findAll();

    Comment findById (Long id);

    Comment save(Comment comment);

    void deleteById(Long id);

    Long count();

}
