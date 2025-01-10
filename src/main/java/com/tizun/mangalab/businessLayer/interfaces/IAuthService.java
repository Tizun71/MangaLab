package com.tizun.mangalab.businessLayer.interfaces;

import com.tizun.mangalab.domainLayer.entity.User;

public interface IAuthService {
	User login(String username, String password);
	User register(User user);
}
