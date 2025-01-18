package com.tizun.mangalab.businessLayer.interfaces;

import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Chapter;

public interface IChapterService {
	List<Chapter> ListOfChapters(long mangaID);
	int SaveAllChapters(List<Long> chapterIDs);
	String GetChapterNameByID(int id);
	Chapter Get(long chapterId);
	Chapter Save(Chapter chapter);
	boolean Delete(long chapterId);
	int GetChapterLength(long mangaID);
	Chapter GetPrevChapter(long mangaID, int chapterNumber);
	Chapter GetNextChapter(long mangaID, int chapterNumber);
}
