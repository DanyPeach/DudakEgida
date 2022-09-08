//package com.example.dudakegida.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class MVCConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/styles/css/**")
//                .addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/styles/images/**")
//                .addResourceLocations("classpath:/static/images/");
//        registry.addResourceHandler("/styles/js/**")
//                .addResourceLocations("classpath:/static/js/");
//        registry.addResourceHandler("/styles/lib/**")
//                .addResourceLocations("classpath:/static/lib/");
//        registry.addResourceHandler("/styles/scss/**")
//                .addResourceLocations("classpath:/static/scss/");
//    }
//}