package com.tizun.mangalab.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.businessLayer.interfaces.ITranslatorService;
import com.tizun.mangalab.domainLayer.entity.Category;
import com.tizun.mangalab.domainLayer.entity.Translator;

@Component
public class ComboBoxHelper {
	@Autowired
	private ITranslatorService _translatorService;
	@Autowired
	private ICategoryService _categoryService;
	
	public List<ComboBoxItem> ListOfTranslators(){
		List<ComboBoxItem> list = new ArrayList<>();
		list.add(new ComboBoxItem(0, "Chọn nhóm dịch"));
		List<Translator> translators = _translatorService.ListOfTranslators(0, 0, null);
		for (Translator translator : translators) {
			list.add(new ComboBoxItem(translator.getTranslatorID(), translator.getTranslatorName()));
		}
		return list;
	}
	
	public List<ComboBoxItem> ListOfCategories(){
		List<ComboBoxItem> list = new ArrayList<>();
		list.add(new ComboBoxItem(0, "Chọn thể loại"));
		List<Category> categories = _categoryService.ListOfCategories(0, 0, null);
		for (Category category : categories) {
			list.add(new ComboBoxItem(category.getCategoryID(), category.getCategoryName()));
		}
		return list;
	}
}
