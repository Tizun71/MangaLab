package com.tizun.mangalab.businessLayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Manga;

import jakarta.transaction.Transactional;

@Service
public class MangaService implements IMangaService{

	@Autowired
	private ICommonRepository<Manga> mangaRepository;
	
	@Override
	public List<Manga> ListOfMangas(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		return mangaRepository.List(page, pageSize, searchValue);
	}

	@Override
	public Manga GetMangaByID(int id) {
		// TODO Auto-generated method stub
		return mangaRepository.Get(id).orElse(null);
	}
	
	@Transactional
	@Override
	public Manga Save(Manga manga) {
		// TODO Auto-generated method stub
		return mangaRepository.Save(manga);
	}
}
