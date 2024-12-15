package com.tizun.mangalab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/dashboard")
    public String home() {
        return "admin/home";
    }
}