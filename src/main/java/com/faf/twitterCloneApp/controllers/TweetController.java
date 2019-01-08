package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.services.TweetService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    TwitterUserService twitterUserServiceImpl;

    @Autowired
    TweetService tweetServiceImpl;

    @GetMapping("/tweetForm")
    public String twittFrom (Model model , Principal principal){

        model.addAttribute("tweet",new Tweet());
        model.addAttribute("userDetails",principal);
        return "tweet/tweetForm";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@ModelAttribute Tweet tweet, Model model , Principal principal){

        tweet.setCreateDate(new Date());
        tweet.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        tweetServiceImpl.save(tweet);

        return "redirect:/twitterUser/homePage";
    }


    @GetMapping("/updateTweet")
    public String updateTwitt (@RequestParam("id") String id, Model model , Principal principal){
        model.addAttribute("tweet", tweetServiceImpl.findById(Long.valueOf(id)));
        model.addAttribute("userDetails",principal);
        return  "tweet/tweetForm";
    }


    @GetMapping("/deleteTweet")
    public String deleteTwitt (@RequestParam("id") String id){
        tweetServiceImpl.deleteById(Long.valueOf(id));
        return  "redirect:/twitterUser/homePage";
    }

    @GetMapping("/retweet")
    public String retweet (@RequestParam(value = "tweetId",required = true) Long tweetId , Principal principal){

        Tweet retweetedTweet = tweetServiceImpl.findById(tweetId);
        Tweet newTweet = new Tweet();
        newTweet.setContent(retweetedTweet.getContent());
        newTweet.setCreateDate(retweetedTweet.getCreateDate());
        newTweet.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        tweetServiceImpl.save(newTweet);
        return "redirect:/twitterUser/homePage";
    }

}
