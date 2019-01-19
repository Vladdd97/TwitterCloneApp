package com.faf.twitterCloneApp.controllers.rest;


import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.services.EmailService;
import com.faf.twitterCloneApp.services.ReactionService;
import com.faf.twitterCloneApp.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ReactionRestController {

    @Autowired
    TweetService tweetServiceImpl;

    @Autowired
    ReactionService reactionServiceImpl;

    @Autowired
    EmailService emailServiceImpl;

    @GetMapping("getTweet")
    public ResponseEntity<Tweet> getTweet(@RequestParam(value = "tweetId", required = true) Long tweetId) {


        return ResponseEntity.status(HttpStatus.OK).body(tweetServiceImpl.findById(Long.valueOf(tweetId)));
    }


    @RequestMapping("/likeTweet")
    public ResponseEntity<?> saveOrUpdate(@RequestParam(value = "username",required = false) String username,
                                                 @RequestParam(value = "tweetId",required = false) Long tweetId,
                                                 Model model , Principal principal) {


        if (!reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId, principal.getName()).isPresent()) {
            Reaction reaction = new Reaction();
            Tweet likedTweet = tweetServiceImpl.findById(tweetId);
            reaction.setTweet(likedTweet);
            reaction.setLikedByUser(principal.getName());
            reaction.setLiked(true);
            reactionServiceImpl.save(reaction);

            if (likedTweet.getTwitterUser().getTwitterUserInfo().getEmail() != null
                    && likedTweet.getTwitterUser().getTwitterUserInfo().getIsEmailNotificationEnabled()) {

                String message = "Hi " + username + "! Your tweet :"
                        + "\n[" + likedTweet.getContent() + "]\n"
                        + " was liked by " + principal.getName() + "!";

                emailServiceImpl.sendEmail(likedTweet.getTwitterUser().getTwitterUserInfo().getEmail(), "vladbantus02@gmail.com", "Your tweet was liked", message);

            }

            return ResponseEntity.status(HttpStatus.CREATED).body(reaction);
        }
        else {
            reactionServiceImpl.deleteById(reactionServiceImpl.findByTweetIdAndLikedByUser(tweetId, principal.getName()).get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
