package com.example.webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loggedInIndex(Model model){
        // if not logged in redirect to index page
        return "index";
    }

}
