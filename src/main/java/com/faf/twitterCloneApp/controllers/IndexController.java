package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.*;
import com.faf.twitterCloneApp.repositories.TweetRepository;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import com.faf.twitterCloneApp.services.EmailService;
import com.faf.twitterCloneApp.services.TweetFollowServiceImpl;
import com.faf.twitterCloneApp.services.TweetService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    TweetService tweetServiceImpl;

    @Autowired
    TwitterUserService twitterUserServiceImpl;


    @Autowired
    TweetFollowServiceImpl tweetFollowServiceImpl;

    @Autowired
    EmailService emailService;


    @GetMapping("/")
    public String index(Model model, Principal principal) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = "dev";
//        System.out.println("password : " + password);
//        System.out.println("PASSWORD : " + bCryptPasswordEncoder.encode(password));
        if (principal != null) {
            return "redirect:/twitterUser/homePage";
        } else {
            return "redirect:/loginPage";
        }
    }



    @GetMapping("/registerPage")
    public String registerPage(Model model, Principal principal) {


        model.addAttribute("newTwitterUser",new TwitterUser());

        return "registerPage";
    }


    @PostMapping("/registerNewUser")
    public String registerNewUser(@ModelAttribute TwitterUser twitterUser, Model model, Principal principal) {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = twitterUser.getPassword();

        twitterUser.setPassword(bCryptPasswordEncoder.encode(password));
        twitterUser.setEnabled(true);

        Authority authority = new Authority();
        authority.setRole("ROLE_USER");
        authority.setTwitterUser(twitterUser);

        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        twitterUser.setAuthorities(authorities);

        twitterUserServiceImpl.save(twitterUser);
        return "redirect:/loginPage";
    }



    @GetMapping("/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout, Principal principal) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("logout", "Logged out from TwitterCloneApp successfully.");
        }
        model.addObject("userDetails", principal);
        model.setViewName("loginPage");
        return model;
    }


    @GetMapping("/logoutPage")
    public ModelAndView logoutPage(Principal principal) {
        ModelAndView model = new ModelAndView();

        model.addObject("userDetails", principal);
        model.setViewName("logoutPage");
        return model;
    }


    @GetMapping("/messagePage")
    public String sendMail(@RequestParam(value = "message",required = true) String message  ,Model model){


        model.addAttribute("message",message);

        return "messagePage";
    }

}
