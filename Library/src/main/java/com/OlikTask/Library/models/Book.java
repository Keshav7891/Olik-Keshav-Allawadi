package com.OlikTask.Library.models;

import java.time.Year;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column(unique = true)
	private String title;

	private UUID authorId;

	private String isbn;

	private Year publicationYear;

	@Column(nullable = true)
	private UUID rentalId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UUID getAuthorId() {
		return authorId;
	}

	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Year getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Year publicationYear) {
		this.publicationYear = publicationYear;
	}

	public UUID getRentalId() {
		return rentalId;
	}

	public void setRentalId(UUID rentalId) {
		this.rentalId = rentalId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authorId=" + authorId + ", isbn=" + isbn
				+ ", publicationYear=" + publicationYear + ", rentalId=" + rentalId + "]";
	}

	public Book(UUID id, String title, UUID authorId, String isbn, Year publicationYear, UUID rentalId) {
		super();
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.rentalId = rentalId;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
