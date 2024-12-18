package com.tizun.mangalab.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.domainLayer.entity.Category;
import com.tizun.mangalab.domainLayer.entity.Manga;

@Controller
public class HomeCustomerController {
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IMangaService mangaService;
	
    @GetMapping("")
    public String home(Model model) {
    	List<Category> categories = categoryService.ListOfCategories(0, 0, null);
    	List<Manga> mangas = mangaService.ListOfMangas(1, 20, null);
    	model.addAttribute("categories", categories);
    	model.addAttribute("mangas", mangas);
        return "customer/home";
    }
}
