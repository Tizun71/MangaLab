package com.tizun.mangalab.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tizun.mangalab.businessLayer.dtos.LoginRequest;
import com.tizun.mangalab.businessLayer.interfaces.IAuthService;
import com.tizun.mangalab.domainLayer.entity.Translator;
import com.tizun.mangalab.domainLayer.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private IAuthService authService;
	
	@GetMapping("/auth/login")
    public String showLoginPage(Model model) {
		LoginRequest loginRequest = new LoginRequest();
		model.addAttribute("loginRequest", loginRequest);
        return "customer/login";
    }
	
	@PostMapping("/auth/authenticate")
	public String login(HttpServletRequest request, @ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		
		User user = authService.login(username, password);
		if (user == null) {
			model.addAttribute("error", true);
			model.addAttribute("isLoggedIn", false);
			model.addAttribute("loginRequest", loginRequest);
			return "customer/login";
		}
		System.out.println(user.getRole());
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		if (session.getAttribute("user") != null) {
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
		
		if (user.getRole().contains("ADMIN"))
			return "redirect:/dashboard";
			
		return "redirect:/home";
	}
	
	@GetMapping("/auth/logout")
    public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
        return "redirect:/auth/login";
    }
	
	@GetMapping("/auth/register")
    public String showRegisterPage(Model model) {
		User user = new User();
		user.setUserID(0);
		model.addAttribute("user", user);
		
        return "customer/register";
    }
	
	@PostMapping("/auth/register-save")
    public String showRegisterPage(@ModelAttribute("user") User user, Model model) {
		if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
			model.addAttribute("msg", "Không được để trống thông tin");
			return "customer/register";
		}
			
		try {
			authService.register(user);
			model.addAttribute("msg", "");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", "Tên đăng nhập hoặc Email đã được sử dụng");
			model.addAttribute("user", user);
	        return "customer/register";
		}		
		return "redirect:/auth/login";
    }
	
	@GetMapping("/auth/access-denied")
    public String showAccessDenied(Model model) {
        return "customer/access-denied";
    }
}
