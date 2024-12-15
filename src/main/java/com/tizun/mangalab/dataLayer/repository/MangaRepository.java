package com.tizun.mangalab.dataLayer.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Manga;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MangaRepository implements ICommonRepository<Manga>{
	// define field for entitymanager
	private EntityManager _entityManager;
	// set up constructor injection
	@Autowired
	public MangaRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	
	@Override
	public List<Manga> List(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		String theQuery = "from Manga";
		if (searchValue != null && !searchValue.isEmpty()) {
	        theQuery += " where MangaName like :searchValue";
	    }
		TypedQuery<Manga> qr = _entityManager.createQuery(theQuery, Manga.class); 
	    if (searchValue != null && !searchValue.isEmpty()) {
	        qr.setParameter("searchValue", "%" + searchValue + "%");
	    }
	    qr.setFirstResult((page - 1) * pageSize);
	    qr.setMaxResults(pageSize);
		return qr.getResultList();
	}

	@Override
	public long Count(String searchValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Manga> Get(int id) {
		// TODO Auto-generated method stub
		String theQuery = "from Manga where MangaID = :id";
		TypedQuery<Manga> qr = _entityManager.createQuery(theQuery, Manga.class);
		qr.setParameter("id", id);
		Manga manga = qr.getSingleResult();
		return Optional.ofNullable(manga);
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean InUsed(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Manga Save(Manga data) {
		// TODO Auto-generated method stub
		Manga savedManga = _entityManager.merge(data);
		return savedManga;
	}
}
