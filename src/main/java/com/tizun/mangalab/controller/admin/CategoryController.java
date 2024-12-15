package com.tizun.mangalab.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.domainLayer.entity.Category;

@Controller
@RequestMapping("/dashboard/categories")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	int PAGE_SIZE = 5;
	
	@GetMapping("")
	public String listCategories() {
		return "admin/categories/category-home";
	}
	
	@GetMapping("/search")
	public String searchCategories(
								@RequestParam(name="page", required = false, defaultValue="1") int page,
								@RequestParam(name="searchValue", required = false, defaultValue="") String searchValue,
								Model model
								) {
		long rowCount = categoryService.CountDataRow(searchValue);
		long pageCount = rowCount % PAGE_SIZE == 0 ? rowCount / PAGE_SIZE : rowCount / PAGE_SIZE + 1;
		List<Category> categories = categoryService.ListOfCategories(page, PAGE_SIZE, searchValue);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", pageCount);
		model.addAttribute("categories", categories);
		return "admin/categories/fragments/category-search ::resultList";
	}
}
