package com.tomik.akzhol.FirstSerurityApp.controllers;

import com.tomik.akzhol.FirstSerurityApp.model.Person;
import com.tomik.akzhol.FirstSerurityApp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {
    @GetMapping()
    public String sayHello(){
        return "hello";
    }
    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }
}
