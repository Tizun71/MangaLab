package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Follow;

public interface IFollowService {
	List<Follow> ListMangaFollow(int userID);
	 int SetFollowStatus(int userID, int mangaID, int follow_status);
}
