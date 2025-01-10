package com.tizun.mangalab.dataLayer.interfaces;

import java.util.List;
import java.util.Optional;

import com.tizun.mangalab.domainLayer.entity.Translator;

public interface ITranslatorRepository {
	List<Translator> List(int page, int pageSize, String searchValue);
	
	// Count the number of searching data row 
	long Count(String searchValue);
	
	Optional<Translator> Get(int id);
	
	boolean Delete(int id);
	
	// Check the data with given id have used in other tables
	boolean InUsed(int id);
	
	Translator Save(Translator data);
	
	long CountNumberManga(int id);
}
