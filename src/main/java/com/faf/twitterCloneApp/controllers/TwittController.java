package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import com.faf.twitterCloneApp.services.TwitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/twitt")
public class TwittController {

    @Autowired
    TwitterUserServiceImpl twitterUserServiceImpl;

    @Autowired
    TwittServiceImpl twittServiceImpl;

    @GetMapping("/twittForm")
    public String twittFrom (Model model , Principal principal){

        model.addAttribute("twitt",new Twitt());
        model.addAttribute("userDetails",principal);
        return "Twitt/twittFrom";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@ModelAttribute Twitt twitt , Model model , Principal principal){

        twitt.setCreateDate(new Date());
        twitt.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        twittServiceImpl.save(twitt);

        model.addAttribute("userDetails",principal);
        return "redirect:/usertwitts";
    }


    @GetMapping("/updateTwitt")
    public String updateTwitt (@RequestParam("id") String id, Model model , Principal principal){
        model.addAttribute("twitt", twittServiceImpl.findById(Long.valueOf(id)));
        model.addAttribute("userDetails",principal);
        return  "Twitt/twittFrom";
    }


    @GetMapping("/deleteTwitt")
    public String deleteTwitt (@RequestParam("id") String id){
        twittServiceImpl.deleteById(Long.valueOf(id));
        return  "redirect:/usertwitts";
    }

}
