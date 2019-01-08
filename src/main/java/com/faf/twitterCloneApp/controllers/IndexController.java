package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Tweet;
import com.faf.twitterCloneApp.models.TweetFollow;
import com.faf.twitterCloneApp.services.TweetFollowServiceImpl;
import com.faf.twitterCloneApp.services.TweetService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class IndexController {


    @Autowired
    TweetService tweetServiceImpl;

    @Autowired
    TwitterUserService twitterUserServiceImpl;


    @Autowired
    TweetFollowServiceImpl tweetFollowServiceImpl;


    @GetMapping("/")
    public String index (Model model , Principal principal){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = "dev";
//        System.out.println("password : " + password);
//        System.out.println("PASSWORD : " + bCryptPasswordEncoder.encode(password));
        if ( principal != null) {
            model.addAttribute("userInfo", twitterUserServiceImpl.findByUsername(principal.getName()).get());
            model.addAttribute("newTweet",new Tweet());
            return "index";
        }
        else{
            return "redirect:/loginPage";
        }
    }


    @GetMapping("/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout , Principal principal){
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("logout", "Logged out from TwitterCloneApp successfully.");
        }
        model.addObject("userDetails",principal);
        model.setViewName("loginPage");
        return model;
    }


    @GetMapping("/logoutPage")
    public ModelAndView logoutPage(Principal principal){
        ModelAndView model = new ModelAndView();

        model.addObject("userDetails",principal);
        model.setViewName("logoutPage");
        return model;
    }

}
