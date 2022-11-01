package com.example.dudakegida.controller;

import com.example.dudakegida.model.Message;
import com.example.dudakegida.model.User;
import com.example.dudakegida.service.MessageService;
import com.example.dudakegida.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/showForum")
    public ModelAndView showForum(ModelAndView modelAndView){
        List<Message> list = messageService.findAll();
        modelAndView.addObject("allMess", list);
        modelAndView.setViewName("forum");
        return modelAndView;
    }

    @GetMapping("/addMessPage")
    public ModelAndView addPostPage(ModelAndView modelAndView){
        modelAndView.setViewName("createNews");
        return modelAndView;
    }

    @PostMapping("/addMess")
    public ModelAndView addPost(ModelAndView modelAndView, @ModelAttribute Message message,
                                Authentication authentication) throws IOException {
        message.setUser(getUser(authentication));
        message.setTime(LocalDateTime.now());
        message.setPartOfMessage(messageService.cutPost(message.getMessage()));
        messageService.save(message);
        List<Message> list = messageService.findAll();
        modelAndView.addObject("allMess", list);
        modelAndView.setViewName("forum");
        return modelAndView;
    }

    @GetMapping("/showPostsByType/{type}")
    public ModelAndView showByType(ModelAndView modelAndView, @PathVariable String type){
        //List<Message> list =  messageService.getByType(type);
        //TODO modelAndView.addObj("list", list of all massages of exact type)
        modelAndView.setViewName("addPostPage");
        return modelAndView;
    }

    private User getUser(Authentication authentication){
        String username = authentication.getName();
        return userService.findByLogin(username);
    }


}
