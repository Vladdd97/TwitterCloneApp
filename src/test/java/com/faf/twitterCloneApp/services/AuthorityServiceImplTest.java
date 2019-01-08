package com.faf.twitterCloneApp.services;

import com.faf.twitterCloneApp.models.Authority;
import com.faf.twitterCloneApp.repositories.AuthorityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AuthorityServiceImplTest {

    @Mock
    private AuthorityRepository authorityRepository;

    private AuthorityServiceImpl authorityServiceImpl;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        authorityServiceImpl = new AuthorityServiceImpl(authorityRepository);
    }

    @Test
    public void findAll() {
        Iterable<Authority> authorities = new ArrayList<>();
        Authority authority1 = new Authority();
        authority1.setId(2L);
        authority1.setRole("USER");
        authority1.setTwitterUser(null);

        Authority authority2 = new Authority();
        authority2.setId(3L);
        authority2.setRole("USER");
        authority2.setTwitterUser(null);

        ((ArrayList<Authority>) authorities).add(authority1);
        ((ArrayList<Authority>) authorities).add(authority2);

        when(authorityRepository.findAll()).thenReturn(authorities);

        assertEquals(authorityServiceImpl.findAll(),authorities);
    }

    @Test
    public void findById() {
        Long authorityId = 1L;

        Authority authority = new Authority();
        authority.setId(authorityId);
        authority.setRole("USER");
        authority.setTwitterUser(null);

        Optional<Authority> optionalAuthority = Optional.of(authority);

        when(authorityRepository.findById(authorityId)).thenReturn(optionalAuthority);
        assertEquals(authorityServiceImpl.findById(authorityId),optionalAuthority.get());
    }

    @Test
    public void save() {

        Authority authority = new Authority();
        authority.setId(5L);
        authority.setRole("USER");
        authority.setTwitterUser(null);

//        Authority authority1 = new Authority();
//        authority1.setId(5L);
//        authority1.setRole("ADMIN");
//        authority1.setTwitterUser(null);

        when(authorityRepository.save(authority)).thenReturn(authority);
        assertEquals(authorityServiceImpl.save(authority),authority);

    }

    @Test
    public void deleteById() {

        Long authorityId = 1L;
        Authority authority = new Authority();
        authority.setId(5L);
        authority.setRole("USER");
        authority.setTwitterUser(null);


//        when(authorityRepository.deleteById(authorityId)).thenReturn(authority);
//        assertEquals(authorityServiceImpl.save(authority),authority);
    }

    @Test
    public void count() {
        Long numberOfAuthorities = 5L;
        when(authorityRepository.count()).thenReturn(numberOfAuthorities);
        assertEquals(authorityServiceImpl.count(),numberOfAuthorities);
    }
}