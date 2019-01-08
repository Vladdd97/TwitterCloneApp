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
}
