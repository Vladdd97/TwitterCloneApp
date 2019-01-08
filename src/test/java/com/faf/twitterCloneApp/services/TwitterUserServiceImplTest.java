package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TwitterUserServiceImplTest {

    @Mock
    private TwitterUserRepository twitterUserRepository;

    private TwitterUserServiceImpl twitterUserServiceImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        twitterUserServiceImpl = new TwitterUserServiceImpl(twitterUserRepository);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
        TwitterUser twitterUser = new TwitterUser(2L,"name","12345",true,
                null,null,null,null,null,null);

        when(twitterUserRepository.save(twitterUser)).thenReturn(twitterUser);
        assertEquals(twitterUserServiceImpl.save(twitterUser),twitterUser);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
        Long numberOfTwitterUsers = 3L;

        when(twitterUserRepository.count()).thenReturn(numberOfTwitterUsers);
        assertEquals(twitterUserServiceImpl.count(),numberOfTwitterUsers);
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findSuggestFollowUsers() {
    }
}