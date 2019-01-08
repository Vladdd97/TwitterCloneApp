package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Comment;
import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.services.TwittFollowServiceImpl;
import com.faf.twitterCloneApp.services.TwittService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/twitterUser")
public class TwitterUserController {

    @Autowired
    TwittService twittServiceImpl;

    @Autowired
    TwittFollowServiceImpl twittFollowServiceImpl;

    @GetMapping("/userDetails")
    public String userDetails (Model model , Principal principal){

        model.addAttribute("userDetails",principal);
        model.addAttribute("followers", twittFollowServiceImpl.findAllByFollowingUsername(principal.getName()));
        model.addAttribute("followings", twittFollowServiceImpl.findAllByFollowerUsername(principal.getName()));
        return "twitterUser/userDetails";
    }

    @GetMapping("/userTwitts")
    public String userTwitts (@RequestParam(value = "username",required = false) String username , Model model , Principal principal){
        Iterable<Twitt> twitts;

        if ( username == null){
            twitts = twittServiceImpl.findAllByTwitterUserUsername(principal.getName());
        }
        else{
            twitts = twittServiceImpl.findAllByTwitterUserUsername(username);
            model.addAttribute("username",username);
        }

        model.addAttribute("userTwitts",twitts);
        model.addAttribute("userDetails",principal);
        model.addAttribute("newComment",new Comment());
        return "twitterUser/userTwitts";
    }


}
