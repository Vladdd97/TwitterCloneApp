package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.util.TweetType;
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

        return "redirect:/twitterUser/profilePage";
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
        return  "redirect:/twitterUser/profilePage";
    }

    @GetMapping("/retweet")
    public String retweet (@RequestParam(value = "tweetId",required = true) Long tweetId , Principal principal){


        if (!tweetServiceImpl.findByParentTweetIdAndTypeAndTwitterUserUsername(tweetId,TweetType.Retweet,principal.getName()).isPresent() ) {

            Long parentTweetId;

            Tweet retweetedTweet = tweetServiceImpl.findById(tweetId);
            Tweet newTweet = new Tweet();
            newTweet.setContent(retweetedTweet.getContent());
            newTweet.setCreateDate(new Date());
            newTweet.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
            newTweet.setType(TweetType.Retweet);

            if (retweetedTweet.getParentTweetId() != null){
                parentTweetId = retweetedTweet.getParentTweetId();
            }
            else{
                parentTweetId = tweetId;
            }

            newTweet.setParentTweetId(parentTweetId);
            tweetServiceImpl.save(newTweet);
            return "redirect:/twitterUser/profilePage";

        }
        else {
            String message = "You have retweeted this tweet already! Check you profile page! ";
            return "redirect:/messagePage?message="+message;
        }

    }


    @GetMapping("/bookmark")
    public String bookmark (@RequestParam(value = "tweetId",required = true) Long tweetId , Principal principal){

        if (!tweetServiceImpl.findByParentTweetIdAndTypeAndTwitterUserUsername(tweetId,TweetType.Bookmark,principal.getName()).isPresent() ) {

            Long parentTweetId;

            Tweet bookmarkedTweet = tweetServiceImpl.findById(tweetId);
            Tweet newTweet = new Tweet();
            newTweet.setContent(bookmarkedTweet.getContent());
            newTweet.setCreateDate(new Date());
            newTweet.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
            newTweet.setType(TweetType.Bookmark);

            if (bookmarkedTweet.getParentTweetId() != null){
                parentTweetId = bookmarkedTweet.getParentTweetId();
            }
            else{
                parentTweetId = tweetId;
            }
            newTweet.setParentTweetId(parentTweetId);
            tweetServiceImpl.save(newTweet);
            return "redirect:/twitterUser/bookmarkPage";

        }
        else {
            String message = "You have bookmarked this tweet already! Check you profile page! ";
            return "redirect:/messagePage?message="+message;
        }
    }


}
