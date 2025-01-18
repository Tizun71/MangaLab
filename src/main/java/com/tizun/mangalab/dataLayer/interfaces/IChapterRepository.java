package com.tizun.mangalab.dataLayer.interfaces;

import java.util.Optional;
import java.util.List;

import com.tizun.mangalab.domainLayer.entity.Chapter;

import jakarta.persistence.TypedQuery;

public interface IChapterRepository {
	public List<Chapter> List(long mangaID);

	public Chapter Get(long id);
	public Chapter Get(long id, int chapterNumber);
	public boolean Delete(long id);

	public Chapter Save(Chapter chapter);

	int Count(long mangaID);
}
