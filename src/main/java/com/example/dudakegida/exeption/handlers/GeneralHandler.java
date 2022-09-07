package com.example.dudakegida.exeption.handlers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GeneralHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionPage(Exception exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Exception" + exception.getClass().getName());
        modelAndView.setViewName("");
        return modelAndView;
    }
}
