package com.tizun.mangalab.businessLayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tizun.mangalab.businessLayer.interfaces.IFollowService;
import com.tizun.mangalab.dataLayer.repository.FollowRepository;
import com.tizun.mangalab.domainLayer.entity.Follow;

@Service
public class FollowService implements IFollowService{
	@Autowired
	FollowRepository followRepository;
	
	public List<Follow> ListMangaFollow(int userID) {
		return followRepository.ListMangaFollow(userID);
	}
	
	@Transactional
	public int SetFollowStatus(int userID, int mangaID, int follow_status) {
		return followRepository.SetFollowStatus(userID, mangaID, follow_status);
	}
}
