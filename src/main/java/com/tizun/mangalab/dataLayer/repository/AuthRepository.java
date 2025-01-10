package com.tizun.mangalab.dataLayer.repository;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.IAuthRepository;
import com.tizun.mangalab.domainLayer.entity.Manga;
import com.tizun.mangalab.domainLayer.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class AuthRepository implements IAuthRepository{
	@Autowired
	private EntityManager _entityManager;

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		try {
	        String theQuery = "from User where Username = :username and Password = :password";
	        TypedQuery<User> qr = _entityManager.createQuery(theQuery, User.class);
	        qr.setParameter("username", username);
	        qr.setParameter("password", password);
	        return qr.getSingleResult();
	    } catch (NoResultException e) {
	        return null; 
	    }
	}

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		user.setRole("READER");
		User savedManga = _entityManager.merge(user);
		return savedManga;
	}
}
