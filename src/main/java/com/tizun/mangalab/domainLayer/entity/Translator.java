package com.tizun.mangalab.domainLayer.entity;

import java.util.Date;

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
@Table(name="translator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Translator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="translatorID")
	private int TranslatorID;
	@Column(name="translator_name")
	private String TranslatorName;
	@Column(name="description")
	private String Description;
	@Column(name="email")
	private String Email;
	
	@Transient
	private long MangaQuantity = 0;
}
