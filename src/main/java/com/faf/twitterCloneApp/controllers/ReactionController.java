package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.services.ReactionService;
import com.faf.twitterCloneApp.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionServiceImpl;

    @Autowired
    private TweetService tweetServiceImpl;


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate (@RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "tweetId",required = false) Long tweetId,
                                Model model , Principal principal){


        if ( !reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId,principal.getName()).isPresent() ){
            Reaction reaction = new Reaction();
            reaction.setTweet(tweetServiceImpl.findById(tweetId));
            reaction.setLikedByUser(principal.getName());
            reaction.setLiked(true);
            reactionServiceImpl.save(reaction);
        }
        else {
            reactionServiceImpl.deleteById(reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId,principal.getName()).get().getId());
        }


        model.addAttribute("userDetails",principal);
        return "redirect:/twitterUser/userTweets?username="+username;
    }

}
