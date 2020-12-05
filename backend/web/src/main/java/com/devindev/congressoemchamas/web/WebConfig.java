package com.devindev.congressoemchamas.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/expense").setViewName("index.html");
        registry.addViewController("/news").setViewName("index.html");
        registry.addViewController("/propositions").setViewName("index.html");
        registry.addViewController("/search").setViewName("index.html");
    }
}
