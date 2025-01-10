package com.tizun.mangalab.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tizun.mangalab.businessLayer.interfaces.ICategoryService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterPhotoService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterService;
import com.tizun.mangalab.businessLayer.interfaces.IFollowService;
import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.domainLayer.entity.Category;
import com.tizun.mangalab.domainLayer.entity.Chapter;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;
import com.tizun.mangalab.domainLayer.entity.Follow;
import com.tizun.mangalab.domainLayer.entity.Manga;
import com.tizun.mangalab.domainLayer.entity.User;
import com.tizun.mangalab.utils.ComboBoxHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("home")
public class HomeCustomerController {
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IMangaService mangaService;
	@Autowired
	IChapterService chapterService;
	@Autowired
	IChapterPhotoService chapterPhotoService;
	@Autowired
	IFollowService followService;
	@Autowired
	ComboBoxHelper _comboBoxHelper;
	
    @GetMapping("")
    public String home(Model model) {
    	List<Category> categories = categoryService.ListOfCategories(0, 0, null);
//    	List<Manga> mangas = mangaService.ListOfMangas(1, 6, null);
    	List<Manga> mangas_hot = mangaService.ListOfMangas(1, 6, null, "TotalView", "desc");
    	model.addAttribute("mangas_hot", mangas_hot);
    	List<Manga> mangas = mangaService.ListOfMangas(1, 12, null, "createdDate", "desc");
    	model.addAttribute("categories", categories);
    	model.addAttribute("mangas", mangas);
        return "customer/home";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(name="searchValue", required = false) String searchValue, Model model) {
    	if (searchValue == null) {
    		searchValue = "";
    	}
    	List<Category> categories = categoryService.ListOfCategories(0, 0, null);
    	List<Manga> mangas = mangaService.ListOfMangas(1, 20, searchValue);
    	model.addAttribute("categories", categories);
    	model.addAttribute("mangas", mangas);
        return "customer/search-list";
    }
    
    @GetMapping("/manga")
    public String mangaDetail(HttpServletRequest request, @RequestParam("mangaID") int mangaID, Model model) {
    	Manga manga = mangaService.GetMangaByID(mangaID);
    	List<Chapter> chapters = chapterService.ListOfChapters(mangaID);
    	if (chapters.isEmpty()) {
    		model.addAttribute("firstChapterID", 0);
    	}
    	else {
    		model.addAttribute("firstChapterID", chapters.get(0).getChapterID());
    	}
    	boolean isFollowed = false;
    	
    	HttpSession session = request.getSession();
    	if (session.getAttribute("user") != null) {
    		User user = (User) session.getAttribute("user");
        	List<Follow> follows = followService.ListMangaFollow(user.getUserID());
        	
        	for (Follow follow : follows) {
        		if (follow.getMangaID() == mangaID && follow.getFollowStatus() == 1) {
        			model.addAttribute("isFollowed", true);
        			isFollowed = true;
        			break;
        		}
        	}
    	}
    	
    	if (isFollowed == false)
    		model.addAttribute("isFollowed", false);
    	
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
    	
    	model.addAttribute("prevChapterID", chapterService.GetPrevChapter(chapter.getMangaID(), chapter.getChapterNumber()).getChapterID());
    	model.addAttribute("nextChapterID", chapterService.GetNextChapter(chapter.getMangaID(), chapter.getChapterNumber()).getChapterID());
    	model.addAttribute("totalChapter", chapterService.GetChapterLength(chapter.getMangaID()));
    	
    	model.addAttribute("comboBoxHelper", _comboBoxHelper.ListOfChapters(chapter.getMangaID()));
    	model.addAttribute("chapter", chapter);
    	model.addAttribute("photos", photos);
    	return "customer/chapter";
    }
    
    @GetMapping("/followList")
    public String followList(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	List<Follow> follows = followService.ListMangaFollow(user.getUserID());
    	
    	List<Manga> mangas = new ArrayList<>();
    	for (Follow follow : follows) {
    		mangas.add(mangaService.GetMangaByID(follow.getMangaID()));
    	}
    	model.addAttribute("mangas", mangas);
        return "customer/follow-list";
    }
    
    @GetMapping("/follow")
    public ResponseEntity<Map<String, String>> follow(HttpServletRequest request, @RequestParam("mangaID") int mangaID, @RequestParam("follow_status") int follow_status) {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	System.out.println("Trạng thái follow " + follow_status);
    	followService.SetFollowStatus(user.getUserID(), mangaID, follow_status);
    	
    	Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Đã thêm vào mục theo dõi.");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/increaseView")
    public ResponseEntity<Map<String, String>> increaseView(@RequestParam("mangaID") int mangaID) {
    	mangaService.increaseView(mangaID);
    	Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Thành công.");
        
        return ResponseEntity.ok(response);
    }
}
