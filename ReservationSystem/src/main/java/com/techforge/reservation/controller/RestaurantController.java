package com.techforge.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

    @GetMapping("/")
    public String register(Model model){
        model.addAttribute("message", "Hello world");
        return "index";
    }

}
