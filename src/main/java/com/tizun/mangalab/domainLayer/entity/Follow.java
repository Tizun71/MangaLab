package com.tizun.mangalab.domainLayer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
@IdClass(FollowID.class)
@Entity
public class Follow {
	 @Id
	@Column(name="userID")
	private int UserID;
	 @Id
	@Column(name="mangaID")
	private int MangaID;
	@Column(name="follow_status")
	private int FollowStatus;
}
