package com.tizun.mangalab.dataLayer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.IChapterPhotoRepository;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ChapterPhotoRepository implements IChapterPhotoRepository {
	private EntityManager _entityManager;
	
	@Autowired
	public ChapterPhotoRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	@Override
	public java.util.List<ChapterPhoto> List(long chapterPhotoID) {
		// TODO Auto-generated method stub
		String theQuery = "from ChapterPhoto where ChapterID = :chapterID order by PhotoNumber asc";
		TypedQuery<ChapterPhoto> qr = _entityManager.createQuery(theQuery, ChapterPhoto.class);
		qr.setParameter("chapterID", chapterPhotoID);
		return qr.getResultList();
	}

	@Override
	public ChapterPhoto Get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChapterPhoto Save(ChapterPhoto chapterPhoto) {
		// TODO Auto-generated method stub
		ChapterPhoto savedChapterPhoto = _entityManager.merge(chapterPhoto);
		return savedChapterPhoto;
	}

}
