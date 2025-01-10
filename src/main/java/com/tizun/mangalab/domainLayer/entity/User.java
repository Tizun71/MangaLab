package com.tizun.mangalab.domainLayer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userID")
	private int UserID;
	@Column(name="username")
	private String Username;
	@Column(name="password")
	private String Password;
	@Column(name="email")
	private String Email;
	@Column(name="role")
	private String Role;
}
