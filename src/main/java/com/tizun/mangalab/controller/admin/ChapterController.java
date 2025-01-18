package com.tizun.mangalab.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tizun.mangalab.businessLayer.interfaces.IChapterPhotoService;
import com.tizun.mangalab.businessLayer.interfaces.IChapterService;
import com.tizun.mangalab.businessLayer.services.ChapterService;
import com.tizun.mangalab.domainLayer.entity.Chapter;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;
import com.tizun.mangalab.utils.EntityNameHelper;
import com.tizun.mangalab.utils.UploadHelper;

@Controller
@RequestMapping("/dashboard/chapters")
public class ChapterController {

	@Autowired
	private IChapterService _chapterService;
	@Autowired
	private IChapterPhotoService _chapterPhotoService;
	@Autowired
	private UploadHelper _uploadHelper;
	@Autowired
	private EntityNameHelper _entityNameHelper;
	
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/mangas_img/";
	
	@GetMapping("")
	public String showEditForm(@RequestParam("mangaId")  int mangaID, 
							   @RequestParam(value = "chapterId", required = false) Integer chapterID,
								Model model) {
		Chapter chapter = new Chapter();
		List<ChapterPhoto> chapterPhotos = new ArrayList<>();
		if (chapterID != null) {
			chapterPhotos = _chapterPhotoService.ListOfChapterPhotos(chapterID);
			chapter = _chapterService.Get(chapterID);
		}
		else {
			chapter.setMangaID(mangaID);
			chapter.setChapterNumber(_chapterService.GetChapterLength(mangaID) + 1);
		}
		
		model.addAttribute("mangaId", mangaID);
		model.addAttribute("chapter", chapter);
		model.addAttribute("chapterPhotos", chapterPhotos);
		System.out.println(chapterPhotos.size());
		return "admin/chapters/chapter-edit-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("chapterId") int id, @RequestParam("mangaId") int mid, RedirectAttributes redirectAttributes) {
		_chapterService.Delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "Xóa chương thành công!");
		return "redirect:/dashboard/mangas/showFormForEdit?mangaId=" + mid;
	}
	
	@GetMapping("/save")
	public ResponseEntity<Map<String, String>> save(@RequestParam("chapterIDs") List<Long> chapterIDs, Model model) {
		_chapterService.SaveAllChapters(chapterIDs);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Chapters saved successfully.");
        
        return ResponseEntity.ok(response);
	}
	
	@PostMapping("/savePhotos")
	public String savePhotos( 	@ModelAttribute("chapter") Chapter chapter,
								@RequestParam(value = "pagePhotos", required = false) List<MultipartFile> pagePhotos,
								Model model
							) throws IOException{
		if (chapter.getChapterID() == 0) {
			chapter = _chapterService.Save(chapter);
		}
		if (!pagePhotos.get(0).isEmpty()) {
			for (int i = pagePhotos.size() - 1; i >= 0; i--) {
			    MultipartFile pagePhoto = pagePhotos.get(i);
			    ChapterPhoto chapterPhoto = new ChapterPhoto();
			    
			    if (pagePhoto != null && !pagePhoto.isEmpty()) {
			        String pageFileName = _uploadHelper.GenerateFileName(pagePhoto);
			        chapterPhoto.setChapterID(chapter.getChapterID());
			        chapterPhoto.setPhotoURL(pageFileName);
			    }

			    ChapterPhoto savedChapterPhoto = _chapterPhotoService.Save(chapterPhoto);
			    String uploadDir = UPLOAD_DIRECTORY + chapter.getMangaID() + '/' + savedChapterPhoto.getChapterID();

			    if (pagePhoto != null && !pagePhoto.isEmpty()) {
			        _uploadHelper.uploadImage(pagePhoto, chapterPhoto.getPhotoURL(), uploadDir);
			    }
			}
		}
		
		return "redirect:/dashboard/mangas/showFormForEdit?mangaId=" + chapter.getMangaID();
	}
}
