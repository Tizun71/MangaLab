package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Manga;

public interface IMangaService {
	List<Manga> ListOfMangas(int page, int pageSize, String searchValue);
	Manga GetMangaByID(int id);
	Manga Save(Manga manga);
}
