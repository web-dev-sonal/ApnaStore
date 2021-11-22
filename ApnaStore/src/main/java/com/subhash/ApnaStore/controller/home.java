package com.subhash.ApnaStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//ctrl+alt+o   for removing unused package

@Controller
public class home {
    @GetMapping("/")
    public static String index(){
        return "home";
    }
}
