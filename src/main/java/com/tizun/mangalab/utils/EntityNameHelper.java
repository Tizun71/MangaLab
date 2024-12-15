package com.tizun.mangalab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterService;

@Component
public class EntityNameHelper {
	@Autowired
	private ICategoryService _categoryService;
	@Autowired
	private IChapterService _chapterService;
	
	public String GetCategoryNameByID(int id) {
		return _categoryService.GetCategoryNameByID(id);
	}
	
	public String GetChapterNameByID(int id) {
		return _chapterService.GetChapterNameByID(id);
	}
}
