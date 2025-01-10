package com.tizun.mangalab.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tizun.mangalab.businessLayer.interfaces.ITranslatorService;
import com.tizun.mangalab.domainLayer.entity.Translator;

@Controller
@RequestMapping("/dashboard/translators")
public class TranslatorController {
	private ITranslatorService _translatorService;
	
	public TranslatorController(ITranslatorService translatorService) {
		_translatorService = translatorService;
	}
	
	final int PAGE_SIZE = 5;
	
	@GetMapping("")
	public String listTranslators(Model model) {
		return "admin/translators/translator-home";
	}
	
	@GetMapping("/search")
	public String searchTranslators(
									@RequestParam(name="page", required = false, defaultValue="1") int page,
									@RequestParam(name="searchValue", required = false, defaultValue="") String searchValue,
									Model model
									) {
		long rowCount = _translatorService.CountDataRow(searchValue);
		long pageCount = rowCount % PAGE_SIZE == 0 ? rowCount / PAGE_SIZE : rowCount / PAGE_SIZE + 1;
		List<Translator> translators = _translatorService.ListOfTranslators(page, PAGE_SIZE, searchValue);
		for (Translator translator : translators) {
			translator.setMangaQuantity(_translatorService.CountDataRowInMangaTable(translator.getTranslatorID()));
		}
		model.addAttribute("page", page);
		model.addAttribute("totalPages", pageCount);
		model.addAttribute("translators", translators);
		return "admin/translators/fragments/translator-search ::resultList";
	}
	
	@GetMapping("/showModalForCreate")
	public String showModalForCreate(Model model) {
		Translator translator = new Translator();
		model.addAttribute("title", "Thêm mới nhóm dịch");
		model.addAttribute("translator", translator);
		return "admin/translators/fragments/translator-update-modal ::resultTranslator";
	}
	
	@GetMapping("/showModalForUpdate")
	public String showModalForUpdate(@RequestParam("translatorId") int id, 
									 Model model) {
		Optional<Translator> translator = _translatorService.GetTranslatorByID(id);
		if (translator.isEmpty()) {
			model.addAttribute("error-message", "Không tìm thấy nhóm dịch");
			return "admin/error";
		}
		model.addAttribute("title", "Chỉnh sửa thông tin nhóm dịch");
		model.addAttribute("translator", translator);
		return "admin/translators/fragments/translator-update-modal ::resultTranslator";
	}
	
	@PostMapping("/save")
	public String updateTranslator(@ModelAttribute("translator") Translator translator) {
		_translatorService.Save(translator);
		return "redirect:/dashboard/translators";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("translatorId") int id, RedirectAttributes redirectAttributes) {
		if (_translatorService.InUsed(id)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Nhóm dịch đang được sử dụng!");
			return "redirect:/dashboard/translators";
		}
		_translatorService.Delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "Xóa nhóm dịch thành công!");
		return "redirect:/dashboard/translators";
	}
}
