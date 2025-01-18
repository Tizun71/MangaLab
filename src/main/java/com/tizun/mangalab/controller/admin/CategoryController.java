package com.tizun.mangalab.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/showModalForCreate")
	public String showModalForCreate(Model model) {
		Category category = new Category();
		model.addAttribute("title", "Thêm mới thể loại");
		model.addAttribute("category", category);
		return "admin/categories/fragments/category-update-modal ::resultCategory";
	}
	
	@GetMapping("/showModalForUpdate")
	public String showModalForUpdate(@RequestParam("categoryId") int id, 
									 Model model) {
		Optional<Category> category = categoryService.GetCategoryByID(id);
		if (category.isEmpty()) {
			model.addAttribute("error-message", "Không tìm thấy thể loại");
			return "admin/error";
		}
		model.addAttribute("title", "Chỉnh sửa thông tin thể loại");
		model.addAttribute("category", category);
		return "admin/categories/fragments/category-update-modal ::resultCategory";
	}
	
	@PostMapping("/save")
	public String updateTranslator(@ModelAttribute("category") Category category) {
		categoryService.Save(category);
		return "redirect:/dashboard/categories";
	}
	
//	@GetMapping("/delete")
//	public String delete(@RequestParam("translatorId") int id, RedirectAttributes redirectAttributes) {
//		if (_translatorService.InUsed(id)) {
//			redirectAttributes.addFlashAttribute("errorMessage", "Nhóm dịch đang được sử dụng!");
//			return "redirect:/dashboard/translators";
//		}
//		_translatorService.Delete(id);
//		redirectAttributes.addFlashAttribute("successMessage", "Xóa nhóm dịch thành công!");
//		return "redirect:/dashboard/translators";
//	}
}
