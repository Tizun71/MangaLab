package com.tizun.mangalab.controller.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

	@ModelAttribute
	public void addGlobalAtributes(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
	}
}
