package com.example.dudakegida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal){
        return principal.toString();
    }
}
