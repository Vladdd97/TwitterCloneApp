package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import com.faf.twitterCloneApp.services.TwitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "Twitt/twittFrom";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@ModelAttribute Twitt twitt , Principal principal){

        twitt.setCreateDate(new Date());
        twitt.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        twittServiceImpl.save(twitt);
        return "redirect:/usertwitts";
    }

}
