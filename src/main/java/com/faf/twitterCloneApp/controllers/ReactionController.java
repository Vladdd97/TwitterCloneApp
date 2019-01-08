package com.faf.twitterCloneApp.controllers;

import com.faf.twitterCloneApp.models.Reaction;
import com.faf.twitterCloneApp.services.ReactionService;
import com.faf.twitterCloneApp.services.TwittService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private ReactionService reactionServiceImpl;

    @Autowired
    private TwittService twittServiceImpl;


    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate (@RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "twittId",required = false) Long twittId,
                                Model model , Principal principal){


        if ( !reactionServiceImpl.findByTwittIdAndLikedByUser(twittId,principal.getName()).isPresent() ){
            Reaction reaction = new Reaction();
            reaction.setTwitt(twittServiceImpl.findById(twittId));
            reaction.setLikedByUser(principal.getName());
            reaction.setLiked(true);
            reactionServiceImpl.save(reaction);
        }
        else {
            reactionServiceImpl.deleteById(reactionServiceImpl.findByTwittIdAndLikedByUser(twittId,principal.getName()).get().getId());
        }


        model.addAttribute("userDetails",principal);
        return "redirect:/twitterUser/userTwitts?username="+username;
    }

}
