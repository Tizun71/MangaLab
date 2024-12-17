package com.tizun.mangalab.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/showAdminLoginPage")
	public String showAdminLoginPage() {
		return "admin/admin-login-page";
	}
	
	@GetMapping("/access-denided")
	public String showAccessDenied() {
		return "access-denied";
	}
}
