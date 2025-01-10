package com.tizun.mangalab.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tizun.mangalab.domainLayer.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
            throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/auth/access-denied");
            return false;
        }
        User user = (User) session.getAttribute("user");
        if (!user.getRole().contains("ADMIN")) {
        	response.sendRedirect("/auth/access-denied");
            return false;
        }	
        return true;
    }
}
