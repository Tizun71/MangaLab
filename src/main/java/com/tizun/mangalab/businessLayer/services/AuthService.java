package com.tizun.mangalab.businessLayer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tizun.mangalab.businessLayer.interfaces.IAuthService;
import com.tizun.mangalab.dataLayer.interfaces.IAuthRepository;
import com.tizun.mangalab.domainLayer.entity.User;

@Service
public class AuthService implements IAuthService{

	@Autowired
	private IAuthRepository authRepository;
	
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return authRepository.login(username, password);
	}

	@Transactional
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		return authRepository.register(user);
	}

}
