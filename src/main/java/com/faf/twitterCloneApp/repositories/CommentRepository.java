package com.faf.twitterCloneApp.repositories;

import com.faf.twitterCloneApp.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
