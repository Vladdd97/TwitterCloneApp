package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.models.TwittFollow;
import com.faf.twitterCloneApp.services.TwittFollowServiceImpl;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/twitterUser")
public class TwitterUserController {

    @Autowired
    TwittServiceImpl twittServiceImpl;

    @Autowired
    TwittFollowServiceImpl twittFollowServiceImpl;

    @GetMapping("/userDetails")
    public String userDetails (Model model , Principal principal){

        model.addAttribute("userDetails",principal);
        //Iterable<TwittFollow> twittFollows = twittFollowServiceImpl.findAllByFollowingUsernameOrFollowerUsername(principal.getName() , principal.getName());

        Iterable<TwittFollow> followings = twittFollowServiceImpl.findAllByFollowingUsername(principal.getName());
        Iterable<TwittFollow> followers = twittFollowServiceImpl.findAllByFollowerUsername(principal.getName());
        model.addAttribute("followings", twittFollowServiceImpl.findAllByFollowingUsername(principal.getName()));
        model.addAttribute("followers", twittFollowServiceImpl.findAllByFollowerUsername(principal.getName()));
        return "twitterUser/userDetails";
    }

    @GetMapping("/userTwitts")
    public String userTwitts (Model model , Principal principal){

        Iterable<Twitt> twitts ;

        twitts = twittServiceImpl.findAllByTwitterUserUsername(principal.getName());

        model.addAttribute("userTwitts",twitts);
        model.addAttribute("userDetails",principal);
        return "twitterUser/userTwitts";
    }

}
