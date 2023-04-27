package com.java.TravelAgency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String returnHomePage() {

        log.info("Getting homepage...");
        return "index";
    }
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        return "loginError";
    }

    @GetMapping(path = "/login")
    public String showLogInForm() {
        return "login";
    }

    @RequestMapping({"","/home"})
    public ModelAndView getHome(){

        return new ModelAndView("index");
    }
    @GetMapping("/register")
    public String showRegistrationForm() {
             return "registerForm";
    }
    @PostMapping("/register")
    public String registrationForm(@RequestParam String role, Model model) {
        if ("agent".equals(role)) {
            return "redirect:/agents/register";
        }
        return "redirect:/customers/register";

    }

}