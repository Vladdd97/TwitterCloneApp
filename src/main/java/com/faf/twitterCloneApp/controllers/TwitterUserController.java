package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Comment;
import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.TweetFollow;
import com.faf.twitterCloneApp.services.TweetFollowService;
import com.faf.twitterCloneApp.services.TweetFollowServiceImpl;
import com.faf.twitterCloneApp.services.TweetService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/twitterUser")
public class TwitterUserController {

    @Autowired
    TweetService tweetServiceImpl;

    @Autowired
    TweetFollowService tweetFollowServiceImpl;

    @Autowired
    TwitterUserService twitterUserServiceImp;

    @GetMapping("/userDetails")
    public String userDetails (Model model , Principal principal){

        model.addAttribute("userDetails",principal);
        model.addAttribute("followers", tweetFollowServiceImpl.findAllByFollowingUsername(principal.getName()));
        model.addAttribute("followings", tweetFollowServiceImpl.findAllByFollowerUsername(principal.getName()));
        return "twitterUser/userDetails";
    }

    @GetMapping("/userTweets")
    public String userTweets (@RequestParam(value = "username",required = false) String username , Model model , Principal principal){
        Iterable<Tweet> tweets;

        if ( username == null){
            tweets = tweetServiceImpl.findAllByTwitterUserUsername(principal.getName());
        }
        else{
            tweets = tweetServiceImpl.findAllByTwitterUserUsername(username);
            model.addAttribute("username",username);
        }

        model.addAttribute("userTweets",tweets);
        model.addAttribute("userDetails",principal);
        model.addAttribute("newComment",new Comment());
        return "twitterUser/userTweets";
    }

    @GetMapping("/followUser")
    public String followUser (@RequestParam(value = "username",required = true) String username , Principal principal){

        TweetFollow tweetFollow = new TweetFollow();

        tweetFollow.setFollower(twitterUserServiceImp.findByUsername(principal.getName()).get());
        tweetFollow.setFollowing(twitterUserServiceImp.findByUsername(username).get());
        tweetFollowServiceImpl.save(tweetFollow);

        return "redirect:/";
    }


}
