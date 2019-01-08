package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Comment;
import com.faf.twitterCloneApp.repositories.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    private CommentServiceImpl commentServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentServiceImpl = new CommentServiceImpl(commentRepository);
    }

    @Test
    public void findAll() {

        Iterable<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setId(4L);
        comment1.setCreateDate(new Date());
        comment1.setContent("content for comment1");
        comment1.setTweet(null);
        comment1.setTwitterUser(null);

        Comment comment2 = new Comment();
        comment2.setId(5L);
        comment2.setCreateDate(new Date());
        comment2.setContent("content for comment2");
        comment2.setTweet(null);
        comment2.setTwitterUser(null);

        ((ArrayList<Comment>) comments).add(comment1);
        ((ArrayList<Comment>) comments).add(comment2);

        when(commentRepository.findAll()).thenReturn(comments);
        assertEquals(commentServiceImpl.findAll(),comments);
        verify(commentRepository,times(1)).findAll();

    }

    @Test
    public void findById() {
        Long commentId = 1L;

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setCreateDate(new Date());
        comment.setContent("content for comment");
        comment.setTweet(null);
        comment.setTwitterUser(null);


        Optional<Comment> optionalComment = Optional.of(comment);

        when(commentRepository.findById(commentId)).thenReturn(optionalComment);
        assertEquals(commentServiceImpl.findById(commentId),optionalComment.get());
        verify(commentRepository,times(1)).findById(commentId);
    }

    @Test
    public void save() {
        Comment comment = new Comment();
        comment.setId(4L);
        comment.setCreateDate(new Date());
        comment.setContent("content for comment");
        comment.setTweet(null);
        comment.setTwitterUser(null);


        when(commentRepository.save(comment)).thenReturn(comment);
        assertEquals(commentRepository.save(comment),comment);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
        Long numberOfComments = 2L;
        when(commentRepository.count()).thenReturn(numberOfComments);
        assertEquals(commentServiceImpl.count(),numberOfComments);
    }
}