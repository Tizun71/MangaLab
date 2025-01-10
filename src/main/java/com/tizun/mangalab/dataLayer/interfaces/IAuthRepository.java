package com.tizun.mangalab.dataLayer.interfaces;

import com.tizun.mangalab.domainLayer.entity.Auth;
import com.tizun.mangalab.domainLayer.entity.User;

public interface IAuthRepository {
	public User login(String username, String password);
	public User register(User user);
}
