package com.tizun.mangalab.dataLayer.interfaces;

import java.util.List;
import java.util.Optional;

import com.tizun.mangalab.domainLayer.entity.Manga;

public interface IMangaRepository {
	List<Manga> List(int page, int pageSize, String searchValue);
	List<Manga> List(int page, int pageSize, String searchValue, String orderby, String orderType);
	// Count the number of searching data row 
	long Count(String searchValue);
	
	Optional<Manga> Get(int id);
	
	boolean Delete(int id);
	
	// Check the data with given id have used in other tables
	boolean InUsed(int id);
	
	Manga Save(Manga data);
}
