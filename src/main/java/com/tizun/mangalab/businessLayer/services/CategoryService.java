package com.tizun.mangalab.businessLayer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Category;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private ICommonRepository<Category> categoryRepository;
	
	@Override
	public List<Category> ListOfCategories(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		return categoryRepository.List(page, pageSize, searchValue);
	}

	@Override
	public String GetCategoryNameByID(int id) {
		// TODO Auto-generated method stub
		Optional<Category> category = categoryRepository.Get(id);
		return category.map(Category::getCategoryName).orElse("");
	}

	@Override
	public long CountDataRow(String searchValue) {
		// TODO Auto-generated method stub
		return categoryRepository.Count(searchValue);
	}

	@Override
	public Optional<Category> GetCategoryByID(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category Save(Category category) {
		// TODO Auto-generated method stub
		return null;
	}
}
