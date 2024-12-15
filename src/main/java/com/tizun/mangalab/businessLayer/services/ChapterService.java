package com.tizun.mangalab.businessLayer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tizun.mangalab.businessLayer.interfaces.IChapterService;
import com.tizun.mangalab.dataLayer.interfaces.IChapterRepository;
import com.tizun.mangalab.domainLayer.entity.Chapter;

import jakarta.transaction.Transactional;

@Service
public class ChapterService implements IChapterService{

	@Autowired
	private IChapterRepository chapterRepository;
	
	@Override
	public List<Chapter> ListOfChapters(long mangaID) {
		// TODO Auto-generated method stub
		return chapterRepository.List(mangaID);
	}

	@Transactional
	@Override
	public int SaveAllChapters(List<Long> chapterIDs) {
		// TODO Auto-generated method stub
		for (int i = 0; i < chapterIDs.size(); i++) {
			Chapter chapter = chapterRepository.Get(chapterIDs.get(i));
			chapter.setChapterNumber(i + 1);
			chapterRepository.Save(chapter);
		}
		return 0;
	}

	@Override
	public String GetChapterNameByID(int id) {
		// TODO Auto-generated method stub
		return chapterRepository.Get(id).getChapterName();
	}

	@Transactional
	@Override
	public Chapter Save(Chapter chapter) {
		// TODO Auto-generated method stub
		return chapterRepository.Save(chapter);
	}

	@Override
	public Chapter Get(long chapterId) {
		// TODO Auto-generated method stub
		return chapterRepository.Get(chapterId);
	}
}
