	package com.tizun.mangalab.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tizun.mangalab.businessLayer.interfaces.IChapterService;
import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.domainLayer.entity.Chapter;
import com.tizun.mangalab.domainLayer.entity.Manga;
import com.tizun.mangalab.domainLayer.entity.Translator;
import com.tizun.mangalab.utils.ComboBoxHelper;
import com.tizun.mangalab.utils.EntityNameHelper;
import com.tizun.mangalab.utils.UploadHelper;

@Controller
@RequestMapping("/dashboard/mangas")
public class MangaController {
	@Autowired
	private IMangaService _mangaService;
	@Autowired
	private EntityNameHelper _entityNameHelper;
	@Autowired
	private ComboBoxHelper _comboBoxHelper;
	@Autowired
	private UploadHelper _uploadHelper;
	@Autowired
	private IChapterService _chapterService;
	
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/mangas_img/";
	
	final int PAGE_SIZE = 5;
	
	@GetMapping("")
	public String listMangas(Model model) {
		return "admin/mangas/manga-home";
	}
	
	@GetMapping("/search")
	public String searchMangas(
			@RequestParam(name="page", required = false, defaultValue="1") int page,
			@RequestParam(name="searchValue", required = false, defaultValue="") String searchValue,
			Model model
			) {
	long rowCount = _mangaService.CountDataRow(searchValue);
	long pageCount = rowCount % PAGE_SIZE == 0 ? rowCount / PAGE_SIZE : rowCount / PAGE_SIZE + 1;
	List<Manga> mangas = _mangaService.ListOfMangas(page, PAGE_SIZE, searchValue);
	model.addAttribute("page", page);
	model.addAttribute("totalPages", pageCount);
	model.addAttribute("mangas", mangas);
	model.addAttribute("entityNameHelper", _entityNameHelper);
	return "admin/mangas/fragments/manga-search ::resultList";
	}
	
	@GetMapping("/showFormForCreate")
	public String showCreateForm(Model model) {
		Manga manga = new Manga();
		manga.setMangaID(0);
		model.addAttribute("comboBoxHelper", _comboBoxHelper);
		model.addAttribute("manga", manga);
		return "admin/mangas/create-form";
	}
	
	@GetMapping("/showFormForEdit")
	public String showEditForm(@RequestParam("mangaId") int id, Model model) {
		model.addAttribute("comboBoxHelper", _comboBoxHelper);
		Manga manga = _mangaService.GetMangaByID(id);
		model.addAttribute("manga", manga);
		List<Chapter> chapters = _chapterService.ListOfChapters(id);
		model.addAttribute("chapters", chapters);
		return "admin/mangas/edit-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("mangaId") int id, RedirectAttributes redirectAttributes) {
		if (_mangaService.InUsed(id)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Truyện đang được sử dụng!");
			return "redirect:/dashboard/mangas";
		}
		_mangaService.Delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "Xóa truyện nhóm dịch thành công!");
		return "redirect:/dashboard/mangas";
	}
	
	@PostMapping("/save")
	public String save( @ModelAttribute("manga") Manga manga,
						@RequestParam("bannerImage") MultipartFile bannerPhoto,
						@RequestParam("displayImage") MultipartFile displayPhoto,
					  	Model model
					   ) throws IOException {
		if (bannerPhoto != null && !bannerPhoto.isEmpty()) {
			String bannerFileName = _uploadHelper.GenerateFileName(bannerPhoto);
			manga.setBannerPhoto(bannerFileName);
		}		
		if (displayPhoto != null && !displayPhoto.isEmpty()) {
			String displayFileName = _uploadHelper.GenerateFileName(displayPhoto);
			manga.setDisplayPhoto(displayFileName);
		}
		
		Manga savedManga = _mangaService.Save(manga);		
		///uploads/mangas_img/mangaID
		String uploadDir = UPLOAD_DIRECTORY + savedManga.getMangaID();
		
		if (bannerPhoto != null && !bannerPhoto.isEmpty()) {
			_uploadHelper.uploadImage(bannerPhoto, manga.getBannerPhoto(), uploadDir);
		}
		if (displayPhoto != null && !displayPhoto.isEmpty()) {
			_uploadHelper.uploadImage(displayPhoto, manga.getDisplayPhoto(), uploadDir);
		}
		
		return "redirect:/dashboard/mangas";
	}
}
