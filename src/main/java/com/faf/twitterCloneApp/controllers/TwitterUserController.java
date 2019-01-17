package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Comment;
import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.TweetFollow;
import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.models.util.TweetType;
import com.faf.twitterCloneApp.models.util.TweetView;
import com.faf.twitterCloneApp.repositories.TweetFollowRepository;
import com.faf.twitterCloneApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/twitterUser")
public class TwitterUserController {

    @Autowired
    TweetService tweetServiceImpl;

    @Autowired
    TweetFollowService tweetFollowServiceImpl;

    @Autowired
    TwitterUserService twitterUserServiceImp;


    @Autowired
    TwitterUserService twitterUserServiceImpl;

    @Autowired
    TweetViewService tweetViewServiceImpl;



    @GetMapping("/homePage")
    public String homePage(Model model, Principal principal) {

        int pageNumber = 0;
        int pageSize = 10;
        Long userId = twitterUserServiceImpl.findByUsername(principal.getName()).get().getId();

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(principal.getName()).get());
        model.addAttribute("newTweet", new Tweet());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("tweets",tweetViewServiceImpl.findAllTweetViewsByOrderByCreateDateDesc(pageNumber,pageSize));
        model.addAttribute("suggestFollowUsers", twitterUserServiceImpl.findSuggestFollowUsers(userId, pageNumber, pageSize));
        model.addAttribute("authenticatedUserUsername",principal.getName());
        return "twitterUser/homePage";
    }



    @GetMapping("/profilePage")
    public String profilePage (@RequestParam(value = "username",required = false) String username , Model model ,Principal principal){

        Boolean isFollowed = false;
        if ( username == null){
            username = principal.getName();
        }
        else{
            Long followerUserId = twitterUserServiceImpl.findByUsername(principal.getName()).get().getId();
            Long followingUserId = twitterUserServiceImpl.findByUsername(username).get().getId();

            if ( tweetFollowServiceImpl.findByFollowingIdAndFollowerId(followingUserId,followerUserId) != null ){
                isFollowed = true;
            }
        }

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(username).get());
        model.addAttribute("tweets",tweetViewServiceImpl.findAllTweetViewsByTwitterUserUsername(username));
        model.addAttribute("newTweet", new Tweet());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("authenticatedUserUsername",principal.getName());
        model.addAttribute("isFollowed",isFollowed);
       return "twitterUser/profilePage";

    }

    @GetMapping("/bookmarkPage")
    public String bookmarkPage(Model model, Principal principal) {

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(principal.getName()).get());
        model.addAttribute("tweets",tweetViewServiceImpl.findAllTweetViewsByTwitterUserUsername(principal.getName()));
        model.addAttribute("newComment", new Comment());
        model.addAttribute("authenticatedUserUsername",principal.getName());

        return "twitterUser/bookmarkPage";
    }


}
