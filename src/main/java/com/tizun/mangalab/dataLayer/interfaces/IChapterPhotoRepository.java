package com.tizun.mangalab.dataLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Chapter;
import com.tizun.mangalab.domainLayer.entity.ChapterPhoto;

public interface IChapterPhotoRepository {
	public List<ChapterPhoto> List(long chapterID);

	public ChapterPhoto Get(long id);

	public boolean Delete(int id);

	public ChapterPhoto Save(ChapterPhoto chapterPhoto);
}
