package com.tizun.mangalab.dataLayer.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.IChapterRepository;
import com.tizun.mangalab.domainLayer.entity.Chapter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ChapterRepository implements IChapterRepository{
	private EntityManager _entityManager;
	
	@Autowired
	public ChapterRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	@Override
	public java.util.List<Chapter> List(long mangaID) {
		// TODO Auto-generated method stub
		String theQuery = "from Chapter where MangaID = :mangaID order by ChapterNumber";
		TypedQuery<Chapter> qr = _entityManager.createQuery(theQuery, Chapter.class);
		qr.setParameter("mangaID", mangaID);
		return qr.getResultList();
	}

	@Override
	public Chapter Get(long id) {
		// TODO Auto-generated method stub
		Chapter chapter = _entityManager.find(Chapter.class, id);
		return chapter;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Chapter Save(Chapter data) {
		// TODO Auto-generated method stub
		Chapter savedChapter = _entityManager.merge(data);
		return savedChapter;
	}
}