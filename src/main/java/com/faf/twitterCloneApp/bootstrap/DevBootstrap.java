package com.faf.twitterCloneApp.bootstrap;

import com.faf.twitterCloneApp.models.Authority;
import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.models.TwittFollow;
import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.repositories.AuthorityRepository;
import com.faf.twitterCloneApp.services.TwittFollowServiceImpl;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import com.faf.twitterCloneApp.services.TwitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TwittServiceImpl twittServiceImpl;

    @Autowired
    private TwitterUserServiceImpl twitterUserServiceImpl;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TwittFollowServiceImpl twittFollowServiceImpl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if ( twitterUserServiceImpl.count() == 0 )
            initData();
    }

    public void initData(){

        initUser1();
        initUser2();
        initTwittFollow();

    }

    public void initUser1(){
        //user 1
        TwitterUser twitterUser1 = new TwitterUser();
        twitterUser1.setUsername("vasile");
        twitterUser1.setPassword("$2a$10$eET7zB2oNuhzsEDUMmy3L.VHvZ/N.NwbD4T5IKPl6cPZ0WNCAuhFG"); //vasile
        twitterUser1.setEnabled(true);


        ArrayList<Twitt> twitts = new ArrayList<>();

        Twitt twitt1 = new Twitt();
        twitt1.setTitle("Title 1");
        twitt1.setContent("Content 1 : Wanna sleep !");
        twitt1.setCreateDate(new Date());
        twitt1.setTwitterUser(twitterUser1);

        Twitt twitt2 = new Twitt();
        twitt2.setTitle("Title 2");
        twitt2.setContent("Content 2 : just need more time for that");
        twitt2.setCreateDate(new Date());
        twitt2.setTwitterUser(twitterUser1);

        twitts.add(twitt1);
        twitts.add(twitt2);

        Authority authority1 = new Authority();
        authority1.setRole("ROLE_USER");
        authority1.setTwitterUser(twitterUser1);

        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(authority1);

        twitterUser1.setTwitts(twitts);
        twitterUser1.setAuthorities(authorities);
        twitterUserServiceImpl.save(twitterUser1);

//        twittServiceImpl.save(twitt1);
//        twittServiceImpl.save(twitt2);
//
//        authorityRepository.save(authority1);
    }

    public void initUser2() {

        //user 2
        TwitterUser twitterUser2 = new TwitterUser();
        twitterUser2.setUsername("dev");
        twitterUser2.setPassword("$2a$10$x2.lce1UfZDumfBN3fcCeOhBaUJT2xEGw9LABlvcuRsDCMXFhLdLy"); //dev
        twitterUser2.setEnabled(true);


        ArrayList<Twitt> twitts = new ArrayList<>();

        Twitt twitt1 = new Twitt();
        twitt1.setTitle("Abduction");
        twitt1.setContent("A thriller centered on a young man who sets out to uncover the truth about his life after finding his baby photo on a missing persons website.");
        twitt1.setCreateDate(new Date());
        twitt1.setTwitterUser(twitterUser2);

        Twitt twitt2 = new Twitt();
        twitt2.setTitle("A Christmas Too Many");
        twitt2.setContent("A Hollywood legend invites her not-so-normal family home for the holidays.");
        twitt2.setCreateDate(new Date());
        twitt2.setTwitterUser(twitterUser2);

        Twitt twitt3 = new Twitt();
        twitt3.setTitle("The Hunger Games");
        twitt3.setContent("Following her rescue from the devastating Quarter Quell, " +
                "Katniss (Jennifer Lawrence) awakes in the complex beneath the supposedly destroyed District 13. " +
                "Her home, District 12, has been reduced to rubble, and Peeta Mellark (Josh Hutcherson) is now the brainwashed " +
                "captive of President Snow (Donald Sutherland). At the same time, Katniss also learns about a secret rebellion spreading " +
                "throughout all of Panem -- a rebellion that will place her at the center of a plot to turn the tables on Snow. ");
        twitt3.setCreateDate(new Date());
        twitt3.setTwitterUser(twitterUser2);

        twitts.add(twitt1);
        twitts.add(twitt2);
        twitts.add(twitt3);

        Authority authority1 = new Authority();
        authority1.setRole("ROLE_USER");
        authority1.setTwitterUser(twitterUser2);

        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(authority1);

        twitterUser2.setTwitts(twitts);
        twitterUser2.setAuthorities(authorities);
        twitterUserServiceImpl.save(twitterUser2);

//        twittServiceImpl.save(twitt1);
//        twittServiceImpl.save(twitt2);
//
//        authorityRepository.save(authority1);

    }

    public void initTwittFollow(){

        TwittFollow twittFollow1 = new TwittFollow();
        twittFollow1.setFollowing(twitterUserServiceImpl.findByUsername("dev").get());
        twittFollow1.setFollower(twitterUserServiceImpl.findByUsername("vasile").get());

        TwittFollow twittFollow2 = new TwittFollow();
        twittFollow2.setFollowing(twitterUserServiceImpl.findByUsername("vasile").get());
        twittFollow2.setFollower(twitterUserServiceImpl.findByUsername("dev").get());

        twittFollowServiceImpl.save(twittFollow1);
        twittFollowServiceImpl.save(twittFollow2);


    }

}
