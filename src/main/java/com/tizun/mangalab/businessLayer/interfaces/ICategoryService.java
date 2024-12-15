package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;
import java.util.Optional;

import com.tizun.mangalab.domainLayer.entity.Category;

public interface ICategoryService {
	List<Category> ListOfCategories(int page, int pageSize, String searchValue);
	String GetCategoryNameByID(int id);
	long CountDataRow(String searchValue);
	Optional<Category> GetCategoryByID(int id);
	boolean Delete(int id);
	Category Save(Category category);
}
