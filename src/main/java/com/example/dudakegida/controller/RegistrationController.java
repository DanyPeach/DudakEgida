package com.example.dudakegida.controller;

import com.example.dudakegida.model.User;
import com.example.dudakegida.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Configuration
@RequestMapping("/new")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registrationPage")
    public ModelAndView registrationPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        userService.save(user);
        modelAndView.setViewName("tem");
        return modelAndView;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

//
//    @ModelAttribute()
//    public void addAttributes(Model model){
//        model.addAttribute("user", new User());
//    }
}
