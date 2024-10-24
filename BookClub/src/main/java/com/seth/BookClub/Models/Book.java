package com.seth.BookClub.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Title is Required!")
	@Size(min=3, max=255, message="Title must be between 3 and 255 characters")
	private String title;
	
	@NotEmpty(message="Author is Required!")
	@Size(min=3, max=255, message="Author must be between 3 and 255 characters")
	private String author;
	
	@NotEmpty(message="Description is Required!")
	@Size(min=3, message="Description must be at least 3 characters")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Book() {
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
