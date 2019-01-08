package com.faf.twitterCloneApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index (){

       return "index";
    }

    @GetMapping("/userdetails")
    public String userDetails (Model model , Principal principal){
        model.addAttribute("userDetails",principal);
        return "userDetails";
    }
}
