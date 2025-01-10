package com.tizun.mangalab.domainLayer.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="manga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mangaID")
	private long MangaID;
	
	@Column(name="manga_name")
	private String MangaName;
	
	@Column(name="description")
	private String Description;
	
	@Column(name="author")
	private String Author;
	
	@Column(name="categoryID")
	private int CategoryID;
	
	@Column(name="translatorID")
	private int TranslatorID;
	
	@Column(name="tags")
	private String Tags;
	
	@Column(name="is_completed")
	private boolean isCompleted = false;
	
	@CreationTimestamp
    @Column(name = "created_date", nullable = true)
    private Date createdDate;
	
	@Column(name="banner_photo")
	private String BannerPhoto;
	
	@Column(name="display_photo")
	private String DisplayPhoto;
	
	@Column(name="total_view")
	private long TotalView;
	
	@Transient
	public String GetIsCompletedString() {
		return isCompleted == true ? "Hoàn thành" : "Chưa hoàn thành";
	}
	
	@Transient
	public String getBannerPhotoPath() {
		if (BannerPhoto == null || MangaID == 0)
			return null;
		return "/uploads/mangas_img/" + MangaID + "/" + BannerPhoto;
	}
	
	@Transient
	public String getDisplayPhotoPath() {
		if (BannerPhoto == null || MangaID == 0)
			return null;
		return "/uploads/mangas_img/" + MangaID + "/" + DisplayPhoto;
	}
	
	@Transient
	public String getTimeAgo() {
		long diffMillis = new Date().getTime() - createdDate.getTime();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(diffMillis);
        if (seconds < 60) {
            return seconds + " giây trước";
        }

        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
        if (minutes < 60) {
            return minutes + " phút trước";
        }

        long hours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        if (hours < 24) {
            return hours + " giờ trước";
        }

        long days = TimeUnit.MILLISECONDS.toDays(diffMillis);
        return days + " ngày trước";
	}
	
	 @PreUpdate
	 public void onUpdate() {
		 this.createdDate = new Date();
	 }
}
