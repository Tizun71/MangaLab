package com.tizun.mangalab.controller.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterPhotoService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterService;
import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.domainLayer.entity.Category;
import com.tizun.mangalab.domainLayer.entity.Chapter;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;
import com.tizun.mangalab.domainLayer.entity.Manga;

@Controller
public class HomeCustomerController {
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IMangaService mangaService;
	@Autowired
	IChapterService chapterService;
	@Autowired
	IChapterPhotoService chapterPhotoService;
	
    @GetMapping("")
    public String home(Model model) {
    	List<Category> categories = categoryService.ListOfCategories(0, 0, null);
    	List<Manga> mangas = mangaService.ListOfMangas(1, 20, null);
    	model.addAttribute("categories", categories);
    	model.addAttribute("mangas", mangas);
        return "customer/home";
    }
    
    @GetMapping("/manga")
    public String mangaDetail(@RequestParam("mangaID") int mangaID, Model model) {
    	Manga manga = mangaService.GetMangaByID(mangaID);
    	List<Chapter> chapters = chapterService.ListOfChapters(mangaID);
    	model.addAttribute("chapters", chapters);
    	model.addAttribute("manga", manga);
    	return "customer/manga-detail";
    }
    
    @GetMapping("/chapter")
    public String chapter(@RequestParam("chapterID") int chapterID, Model model) {
    	Chapter chapter = chapterService.Get(chapterID);
    	
    	List<ChapterPhoto> chapterPhotos = new ArrayList<>();
    	chapterPhotos = chapterPhotoService.ListOfChapterPhotos(chapterID);
    	
    	List<String> photos = new ArrayList<>();
    	for (ChapterPhoto chapterPhoto : chapterPhotos) {
    		photos.add(chapterPhoto.getPhotoURL(chapter.getMangaID(), chapter.getChapterID()));
    	}
    	
    	
    	
    	model.addAttribute("chapter", chapter);
    	model.addAttribute("photos", photos);
    	return "customer/chapter";
    }
}
