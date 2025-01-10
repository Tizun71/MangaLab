package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tizun.mangalab.domainLayer.entity.Translator;

@Service
public interface ITranslatorService {
	List<Translator> ListOfTranslators(int page, int pageSize, String searchValue);
	long CountDataRow(String searchValue);
	long CountDataRowInMangaTable(int id);
	Optional<Translator> GetTranslatorByID(int id);
	boolean Delete(int id);
	boolean InUsed(int id);
	Translator Save(Translator translator);
}
