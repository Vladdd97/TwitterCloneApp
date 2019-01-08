package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.services.TweetFollowService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/tweetFollow")
public class TweetFollowController {

    @Autowired
    TweetFollowService tweetFollowServiceImpl;

    @Autowired
    TwitterUserService twitterUserServiceImpl;


    @GetMapping("/followers")
    public String followers(@RequestParam(value = "username", required = false) String username, Model model, Principal principal) {

        if ( username == null){
            username = principal.getName();
        }

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(username).get());
        model.addAttribute("authenticatedUserUsername", principal.getName());
        model.addAttribute("followers", tweetFollowServiceImpl.findAllByFollowingUsername(username));
        return "tweetFollow/tweetFollow";
    }

    @GetMapping("/followings")
    public String followings(@RequestParam(value = "username", required = false) String username, Model model, Principal principal) {

        if ( username == null){
            username = principal.getName();
        }

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(username).get());
        model.addAttribute("authenticatedUserUsername", principal.getName());
        model.addAttribute("followings", tweetFollowServiceImpl.findAllByFollowerUsername(username));
        return "tweetFollow/tweetFollow";
    }


}
