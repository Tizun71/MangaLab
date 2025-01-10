package com.tizun.mangalab.dataLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Follow;

public interface IFollowRepository {
	List<Follow> ListMangaFollow(int userID);

	int SetFollowStatus(int userID, int mangaID, int followStatus);
}
