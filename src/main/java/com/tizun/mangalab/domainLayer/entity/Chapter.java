package com.tizun.mangalab.domainLayer.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="chapter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="chapterID")
	private long ChapterID;
	@Column(name="chapter_name")
	private String ChapterName;
	@Column(name="mangaID")
	private long MangaID;
	@Column(name="created_date")
	private Date CreatedDate;
	@Column(name="chapter_number")
	private int ChapterNumber;
}
