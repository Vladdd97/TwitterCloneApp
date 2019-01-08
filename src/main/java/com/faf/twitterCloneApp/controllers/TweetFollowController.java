package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.TweetFollow;
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


    @GetMapping("/connections")
    public String connections(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "connectionType", required = false) String connectionType,
                              Model model, Principal principal) {

        if (username == null) {
            username = principal.getName();
        }

        switch (connectionType) {
            case "followers": {
                model.addAttribute("followers", tweetFollowServiceImpl.findAllByFollowingUsername(username));
                break;
            }
            case "followings": {
                model.addAttribute("followings", tweetFollowServiceImpl.findAllByFollowerUsername(username));
                break;
            }
            case "all": {
                model.addAttribute("followings", tweetFollowServiceImpl.findAllByFollowerUsername(username));
                model.addAttribute("followers", tweetFollowServiceImpl.findAllByFollowingUsername(username));
                break;
            }
            default: {
                model.addAttribute("followings", tweetFollowServiceImpl.findAllByFollowerUsername(username));
                model.addAttribute("followers", tweetFollowServiceImpl.findAllByFollowingUsername(username));
                break;
            }
        }

        model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(username).get());
        model.addAttribute("authenticatedUserUsername", principal.getName());
        return "tweetFollow/tweetFollow";
    }


    @GetMapping("/followUser")
    public String followUser(@RequestParam(value = "username", required = true) String username, Principal principal) {

        TweetFollow tweetFollow = new TweetFollow();

        tweetFollow.setFollower(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        tweetFollow.setFollowing(twitterUserServiceImpl.findByUsername(username).get());
        tweetFollowServiceImpl.save(tweetFollow);

        return "redirect:/twitterUser/profilePage";
    }

    @GetMapping("/unfollowUser")
    public String unfollowUser(@RequestParam(value = "username", required = true) String username, Principal principal) {


        Long followerUserId = twitterUserServiceImpl.findByUsername(principal.getName()).get().getId();
        Long followingUserId = twitterUserServiceImpl.findByUsername(username).get().getId();

        Long tweetFollowId = tweetFollowServiceImpl.findByFollowingIdAndFollowerId(followingUserId, followerUserId).getId();

        tweetFollowServiceImpl.deleteById(tweetFollowId);

        return "redirect:/twitterUser/profilePage";
    }


}
