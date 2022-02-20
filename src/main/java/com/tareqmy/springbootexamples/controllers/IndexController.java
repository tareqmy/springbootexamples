package com.tareqmy.springbootexamples.controllers;

import com.tareqmy.springbootexamples.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String getHome() {
        return SecurityUtils.isAnonymousUser() ? "index" : "redirect:/home";
    }

    @GetMapping("/home")
    public String getUserHome() {
        return "home";
    }
}

