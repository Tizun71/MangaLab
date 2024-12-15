package com.tizun.mangalab.businessLayer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.ITranslatorService;
import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Translator;

import jakarta.transaction.Transactional;

@Service
public class TranslatorService implements ITranslatorService{
	
	@Autowired
	private ICommonRepository<Translator> translatorRepository;
	
	@Override
	public List<Translator> ListOfTranslators(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		return translatorRepository.List(page, pageSize, searchValue);
	}
	
	@Override
	public long CountDataRow(String searchValue) {
		// TODO Auto-generated method stub
		return translatorRepository.Count(searchValue);
	}

	@Override
	public Optional<Translator> GetTranslatorByID(int id) {
		// TODO Auto-generated method stub
		return translatorRepository.Get(id);
	}

	@Transactional
	@Override
	public Translator Save(Translator translator) {
		// TODO Auto-generated method stub
		return translatorRepository.Save(translator);
	}

	@Transactional
	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return translatorRepository.Delete(id);
	}
}
