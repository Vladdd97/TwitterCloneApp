package com.faf.twitterCloneApp.bootstrap;

import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import com.faf.twitterCloneApp.services.TwitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TwittServiceImpl twittServiceImpl;

    @Autowired
    private TwitterUserServiceImpl twitterUserServiceImpl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if ( twitterUserServiceImpl.count() == 0 )
            initData();
    }

    public void initData(){

        //user 1
        TwitterUser twitterUser1 = new TwitterUser();
        twitterUser1.setUsername("Vasile");
        twitterUser1.setPassword("$2a$10$eET7zB2oNuhzsEDUMmy3L.VHvZ/N.NwbD4T5IKPl6cPZ0WNCAuhFG"); //vasile
        twitterUser1.setEnabled(true);


        ArrayList<Twitt> twitts = new ArrayList<>();

        Twitt twitt1 = new Twitt();
        twitt1.setTitle("Title 1");
        twitt1.setContent("Content 1 : Wanna sleep !");
        twitt1.setTwitterUser(twitterUser1);

        Twitt twitt2 = new Twitt();
        twitt2.setTitle("Title 2");
        twitt2.setContent("Content 2 : just need more time for that");
        twitt2.setTwitterUser(twitterUser1);



        twitts.add(twitt1);
        twitts.add(twitt2);

        twitterUser1.setTwitts(twitts);
        twitterUserServiceImpl.save(twitterUser1);

        twittServiceImpl.save(twitt1);
        twittServiceImpl.save(twitt2);




    }

}
