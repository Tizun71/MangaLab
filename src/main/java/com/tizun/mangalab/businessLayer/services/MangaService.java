package com.tizun.mangalab.businessLayer.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.IMangaService;
import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.dataLayer.interfaces.IMangaRepository;
import com.tizun.mangalab.domainLayer.entity.Manga;

import jakarta.transaction.Transactional;

@Service
public class MangaService implements IMangaService{

	@Autowired
	private IMangaRepository mangaRepository;
	
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
		manga.setCreatedDate(new Date());
		// TODO Auto-generated method stub
		return mangaRepository.Save(manga);
	}

	@Override
	public long CountDataRow(String searchValue) {
		// TODO Auto-generated method stub
		return mangaRepository.Count(searchValue);
	}

	@Transactional
	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return mangaRepository.Delete(id);
	}

	@Override
	public boolean InUsed(int id) {
		// TODO Auto-generated method stub
		return mangaRepository.InUsed(id);
	}

	@Transactional
	@Override
	public void increaseView(int mangaID) {
		Manga manga = mangaRepository.Get(mangaID).orElse(null);
		if (manga == null)
			return;
		manga.setTotalView(manga.getTotalView() + 1);
		mangaRepository.Save(manga);
	}

	@Override
	public List<Manga> ListOfMangas(int page, int pageSize, String searchValue, String orderBy, String orderType) {
		// TODO Auto-generated method stub
		return mangaRepository.List(page, pageSize, searchValue, orderBy, orderType);
	}
}
