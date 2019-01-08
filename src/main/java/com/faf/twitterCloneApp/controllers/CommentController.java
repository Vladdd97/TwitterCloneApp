package com.faf.twitterCloneApp.controllers;


import com.faf.twitterCloneApp.models.Comment;
import com.faf.twitterCloneApp.services.CommentService;
import com.faf.twitterCloneApp.services.TwittService;
import com.faf.twitterCloneApp.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService commentServiceImpl;

    @Autowired
    private TwittService twittServiceImpl;

    @Autowired
    private TwitterUserService twitterUserServiceImpl;

//    @GetMapping("/commentForm")
//    public String commentForm (Model model , Principal principal){
////
////        model.addAttribute("twitt",new Twitt());
////        model.addAttribute("userDetails",principal);
//        model.addAttribute("comment",new Comment());
//        return "comment/commentForm";
//    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@RequestParam(value = "twittId",required = false) Long twittId,
                                @RequestParam(value = "username",required = false) String username,
                                @ModelAttribute Comment comment , Model model , Principal principal){

        comment.setCreateDate(new Date());
        comment.setTwitt(twittServiceImpl.findById(twittId));
        comment.setTwitterUser(twitterUserServiceImpl.findByUsername(principal.getName()).get());
        commentServiceImpl.save(comment);



        model.addAttribute("userDetails",principal);
        return "redirect:/twitterUser/userTwitts?username="+username;
    }


}
