package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;

public interface IChapterPhotoService {
	List<ChapterPhoto> ListOfChapterPhotos(long chapterPhotoID);
	ChapterPhoto Save(ChapterPhoto chapterPhoto);
}
