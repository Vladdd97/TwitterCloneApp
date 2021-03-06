package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.services.EmailService;
import com.faf.twitterCloneApp.services.ReactionService;
import com.faf.twitterCloneApp.services.TweetService;
import com.faf.twitterCloneApp.services.TwitterUserService;
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

    @Autowired
    EmailService emailService;

    @Autowired
    TwitterUserService twitterUserServiceImpl;


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate (@RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "tweetId",required = false) Long tweetId,
                                Model model , Principal principal){


        if ( !reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId,principal.getName()).isPresent() ){
            Reaction reaction = new Reaction();
            Tweet likedTweet = tweetServiceImpl.findById(tweetId);
            reaction.setTweet(likedTweet);
            reaction.setLikedByUser(principal.getName());
            reaction.setLiked(true);
            reactionServiceImpl.save(reaction);

            if (likedTweet.getTwitterUser().getTwitterUserInfo().getEmail() != null
                    && likedTweet.getTwitterUser().getTwitterUserInfo().getIsEmailNotificationEnabled()){

                String message = "Hi " +username +"! Your tweet :"
                        +"\n[" + likedTweet.getContent() + "]\n"
                        +" was liked by " + principal.getName() + "!";

                emailService.sendEmail(likedTweet.getTwitterUser().getTwitterUserInfo().getEmail(),"vladbantus02@gmail.com","Your tweet was liked",message);

            }


        }
        else {
            reactionServiceImpl.deleteById(reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId,principal.getName()).get().getId());
        }


        model.addAttribute("userDetails",principal);
        return "redirect:/twitterUser/profilePage?username="+username;
    }

}
