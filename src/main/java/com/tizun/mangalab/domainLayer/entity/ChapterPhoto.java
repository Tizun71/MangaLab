package com.tizun.mangalab.domainLayer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="chapter_photo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterPhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="photoID")
	private long PhotoID;
	@Column(name="photo_number")
	private int PhotoNumber;
	@Column(name="photo_url")
	private String PhotoURL;
	@Column(name="chapterID")
	private long ChapterID;
	
	@Transient
	public String getPhotoURL(long mangaID, long chapterID) {
		if (PhotoURL == null || PhotoID == 0)
			return null;
		return "/uploads/mangas_img/" + mangaID + "/" + chapterID + "/" + PhotoURL;
	}
}
