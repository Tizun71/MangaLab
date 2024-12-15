package com.tizun.mangalab.dataLayer.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICommonRepository<T> {
	// Returns a paginated list of data
	// If searchValue is provided, it filters the data accordingly. 
	// If searchValue is null, it returns all data.
	List<T> List(int page, int pageSize, String searchValue);
	
	// Count the number of searching data row 
	long Count(String searchValue);
	
	Optional<T> Get(int id);
	
	boolean Delete(int id);
	
	// Check the data with given id have used in other tables
	boolean InUsed(int id);
	
	T Save(T data);
}
