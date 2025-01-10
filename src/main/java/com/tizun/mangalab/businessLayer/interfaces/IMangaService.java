package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Manga;

public interface IMangaService {
	List<Manga> ListOfMangas(int page, int pageSize, String searchValue);
	List<Manga> ListOfMangas(int page, int pageSize, String searchValue, String orderBy, String orderType);
	long CountDataRow(String searchValue);
	boolean Delete(int id);
	boolean InUsed(int id);
	Manga GetMangaByID(int id);
	Manga Save(Manga manga);
	
	void increaseView(int mangaID);
}
