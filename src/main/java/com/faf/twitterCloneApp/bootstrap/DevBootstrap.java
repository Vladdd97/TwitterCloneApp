package com.faf.twitterCloneApp.bootstrap;

import com.faf.twitterCloneApp.models.*;
import com.faf.twitterCloneApp.models.util.Gender;
import com.faf.twitterCloneApp.repositories.AuthorityRepository;
import com.faf.twitterCloneApp.services.CommentServiceImpl;
import com.faf.twitterCloneApp.services.TweetFollowServiceImpl;
import com.faf.twitterCloneApp.services.TweetServiceImpl;
import com.faf.twitterCloneApp.services.TwitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private TwitterUserServiceImpl twitterUserServiceImpl;

    @Autowired
    private TweetFollowServiceImpl tweetFollowServiceImpl;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

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
        int count = 0 ;

        for ( count = 0 ; count < 10 ; count++) {
            //user 1
            TwitterUser twitterUser1 = new TwitterUser();
            twitterUser1.setUsername("vasile" + count);
            twitterUser1.setPassword("$2a$10$eET7zB2oNuhzsEDUMmy3L.VHvZ/N.NwbD4T5IKPl6cPZ0WNCAuhFG"); //vasile
            twitterUser1.setEnabled(true);

            TwitterUserInfo twitterUserInfo = new TwitterUserInfo();
            twitterUserInfo.setGender(Gender.Male);
            twitterUserInfo.setEmail("vladislav.bantus@faf.utm.md");
            twitterUserInfo.setTwitterUser(twitterUser1);
            twitterUser1.setTwitterUserInfo(twitterUserInfo);


            ArrayList<Tweet> tweets = new ArrayList<>();

            Tweet tweet1 = new Tweet();
            tweet1.setContent("Content 1 : Wanna sleep !");
            tweet1.setCreateDate(new Date());
            tweet1.setTwitterUser(twitterUser1);

            Tweet tweet2 = new Tweet();
            tweet2.setContent("Content 2 : just need more time for that");
            tweet2.setCreateDate(new Date());
            tweet2.setTwitterUser(twitterUser1);

            // add comment
            Comment comment = new Comment();
            comment.setContent("This is a simple comment ...");
            comment.setCreateDate(new Date());
            comment.setTweet(tweet2);
            comment.setTwitterUser(twitterUser1);


            tweets.add(tweet1);
            tweets.add(tweet2);

            Authority authority1 = new Authority();
            authority1.setRole("ROLE_USER");
            authority1.setTwitterUser(twitterUser1);

            ArrayList<Authority> authorities = new ArrayList<>();
            authorities.add(authority1);

            twitterUser1.setTweets(tweets);
            twitterUser1.setAuthorities(authorities);
            twitterUserServiceImpl.save(twitterUser1);

            //comment
            commentServiceImpl.save(comment);

//        twittServiceImpl.save(tweet1);
//        twittServiceImpl.save(tweet2);
//
//        authorityRepository.save(authority1);
        }
    }

    public void initUser2() {

        //user 2
        TwitterUser twitterUser2 = new TwitterUser();
        twitterUser2.setUsername("dev");
        twitterUser2.setPassword("$2a$10$x2.lce1UfZDumfBN3fcCeOhBaUJT2xEGw9LABlvcuRsDCMXFhLdLy"); //dev
        twitterUser2.setEnabled(true);


        ArrayList<Tweet> tweets = new ArrayList<>();

        Tweet tweet1 = new Tweet();
        tweet1.setContent("A thriller centered on a young man who sets out to uncover the truth about his life after finding his baby photo on a missing persons website.");
        tweet1.setCreateDate(new Date());
        tweet1.setTwitterUser(twitterUser2);

        TwitterUserInfo twitterUserInfo = new TwitterUserInfo();
        twitterUserInfo.setGender(Gender.Male);
        twitterUserInfo.setEmail("vladislav.bantus@faf.utm.md");
        twitterUserInfo.setTwitterUser(twitterUser2);
        twitterUser2.setTwitterUserInfo(twitterUserInfo);

        Tweet tweet2 = new Tweet();
        tweet2.setContent("A Hollywood legend invites her not-so-normal family home for the holidays.");
        tweet2.setCreateDate(new Date());
        tweet2.setTwitterUser(twitterUser2);

        Tweet tweet3 = new Tweet();
        tweet3.setContent("Following her rescue from the devastating Quarter Quell, " +
                "Katniss (Jennifer Lawrence) awakes in the complex beneath the supposedly destroyed District 13. " +
                "Her home, District 12, has been reduced to rubble, and Peeta Mellark (Josh Hutcherson) is now the brainwashed " +
                "captive of President Snow (Donald Sutherland). At the same time, Katniss also learns about a secret rebellion spreading " +
                "throughout all of Panem -- a rebellion that will place her at the center of a plot to turn the tables on Snow. ");
        tweet3.setCreateDate(new Date());
        tweet3.setTwitterUser(twitterUser2);

        tweets.add(tweet1);
        tweets.add(tweet2);
        tweets.add(tweet3);

        Authority authority1 = new Authority();
        authority1.setRole("ROLE_USER");
        authority1.setTwitterUser(twitterUser2);

        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(authority1);

        twitterUser2.setTweets(tweets);
        twitterUser2.setAuthorities(authorities);
        twitterUserServiceImpl.save(twitterUser2);

//        twittServiceImpl.save(tweet1);
//        twittServiceImpl.save(tweet2);
//
//        authorityRepository.save(authority1);

    }

    public void initTwittFollow(){

        for (int count = 0 ; count < 8 ; count ++) {
            TweetFollow tweetFollow1 = new TweetFollow();
            tweetFollow1.setFollowing(twitterUserServiceImpl.findByUsername("dev").get());
            tweetFollow1.setFollower(twitterUserServiceImpl.findByUsername("vasile" + count).get());
            tweetFollowServiceImpl.save(tweetFollow1);
        }

        for ( int count = 3 ; count < 5 ; count++) {
            TweetFollow tweetFollow2 = new TweetFollow();
            tweetFollow2.setFollowing(twitterUserServiceImpl.findByUsername("vasile1").get());
            tweetFollow2.setFollower(twitterUserServiceImpl.findByUsername("vasile" + count).get());

            tweetFollowServiceImpl.save(tweetFollow2);
        }
        TweetFollow tweetFollow2 = new TweetFollow();
        tweetFollow2.setFollowing(twitterUserServiceImpl.findByUsername("vasile1").get());
        tweetFollow2.setFollower(twitterUserServiceImpl.findByUsername("dev").get());

        tweetFollowServiceImpl.save(tweetFollow2);

        tweetFollow2 = new TweetFollow();
        tweetFollow2.setFollowing(twitterUserServiceImpl.findByUsername("vasile4").get());
        tweetFollow2.setFollower(twitterUserServiceImpl.findByUsername("dev").get());

        tweetFollowServiceImpl.save(tweetFollow2);

        tweetFollow2 = new TweetFollow();
        tweetFollow2.setFollowing(twitterUserServiceImpl.findByUsername("vasile9").get());
        tweetFollow2.setFollower(twitterUserServiceImpl.findByUsername("dev").get());

        tweetFollowServiceImpl.save(tweetFollow2);

        tweetFollowServiceImpl.save(tweetFollow2);
    }

}
