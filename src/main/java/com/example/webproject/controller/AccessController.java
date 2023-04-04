package com.example.webproject.controller;

import com.example.webproject.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController implements org.springframework.boot.web.servlet.error.ErrorController {
    private static final String PATH = "/nonExisting";

    @GetMapping("/forbidden")
    public String forbidden(){
        return "/forbidden";
    }


    @RequestMapping(PATH)
    public String handleError() {
        throw new NotFoundException("The requested page was not found.");
    }

}
