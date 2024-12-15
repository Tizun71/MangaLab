package com.tizun.mangalab.businessLayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.IChapterPhotoService;
import com.tizun.mangalab.dataLayer.interfaces.IChapterPhotoRepository;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;

import jakarta.transaction.Transactional;

@Service
public class ChapterPhotoService implements IChapterPhotoService{

	@Autowired
	private IChapterPhotoRepository chapterPhotoRepository;

	@Override
	public List<ChapterPhoto> ListOfChapterPhotos(long chapterPhotoID) {
		// TODO Auto-generated method stub
		return chapterPhotoRepository.List(chapterPhotoID);
	}

	@Transactional
	@Override
	public ChapterPhoto Save(ChapterPhoto chapterPhoto) {
		// TODO Auto-generated method stub
		return chapterPhotoRepository.Save(chapterPhoto);
	}
}
