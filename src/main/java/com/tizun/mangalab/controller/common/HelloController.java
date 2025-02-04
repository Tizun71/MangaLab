package com.tizun.mangalab.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	// create a mapping for "/hello"
	@GetMapping("/hello")
	public String sayHello(Model model) {
		model.addAttribute("theDate", java.time.LocalDateTime.now());
		return "Hello World";
	}
}
