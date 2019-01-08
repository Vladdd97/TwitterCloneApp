package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Twitt;
import com.faf.twitterCloneApp.models.TwitterUser;
import com.faf.twitterCloneApp.repositories.TwittRepository;
import com.faf.twitterCloneApp.repositories.TwitterUserRepository;
import com.faf.twitterCloneApp.services.TwittServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class IndexController {


    @Autowired
    TwittServiceImpl twittServiceImpl;


    @GetMapping("/")
    public String index (){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = "dev";
//        System.out.println("password : " + password);
//        System.out.println("PASSWORD : " + bCryptPasswordEncoder.encode(password));
       return "index";
    }

    @GetMapping("/userdetails")
    public String userDetails (Model model , Principal principal){
        model.addAttribute("userDetails",principal);
        return "userDetails";
    }

    @GetMapping("/usertwitts")
    public String userTwitts (Model model , Principal principal){

        Iterable<Twitt> twitts ;
        String username = principal.getName();

        twitts = twittServiceImpl.findAllByTwitterUserUsername(username);

        model.addAttribute("userTwitts",twitts);
        model.addAttribute("username",username);
        return "userTwitts";
    }

    @GetMapping("/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout){
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("logout", "Logged out from TwitterCloneApp successfully.");
        }

        model.setViewName("loginPage");
        return model;
    }


    @GetMapping("/logoutPage")
    public ModelAndView logoutPage(){
        ModelAndView model = new ModelAndView();

        model.setViewName("logoutPage");
        return model;
    }

}
