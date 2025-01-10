package com.tizun.mangalab.dataLayer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.IFollowRepository;
import com.tizun.mangalab.domainLayer.entity.Follow;
import com.tizun.mangalab.domainLayer.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class FollowRepository implements IFollowRepository{
	private EntityManager _entityManager;
	
	@Autowired
	public FollowRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	
	@Override
	public List<Follow> ListMangaFollow(int userID) {
		// TODO Auto-generated method stub
		try {
	        String theQuery = "from Follow where UserID = :id and FollowStatus = 1";
	        TypedQuery<Follow> qr = _entityManager.createQuery(theQuery, Follow.class);
	        qr.setParameter("id", userID);
	        return qr.getResultList();
	    } catch (NoResultException e) {
	        return null; 
	    }
	}

	@Override
	public int SetFollowStatus(int userID, int mangaID, int followStatus) {
		// TODO Auto-generated method stub
		try {
	        String checkQuery = "from Follow where UserID = :userId and MangaID = :mangaId";
	        TypedQuery<Follow> query = _entityManager.createQuery(checkQuery, Follow.class);
	        query.setParameter("userId", userID);
	        query.setParameter("mangaId", mangaID);
	        
	        List<Follow> results = query.getResultList();

	        if (!results.isEmpty()) {
	        	System.out.println("Update follow");
	            Follow existingFollow = results.get(0);
	            existingFollow.setFollowStatus(followStatus);
	            _entityManager.merge(existingFollow);
	            return 1;
	        } else {
	        	System.out.println("Add follow");
	            Follow newFollow = new Follow();
	            newFollow.setUserID(userID);
	            newFollow.setMangaID(mangaID);
	            newFollow.setFollowStatus(followStatus);
	            _entityManager.persist(newFollow);
	            return 1;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}
}
