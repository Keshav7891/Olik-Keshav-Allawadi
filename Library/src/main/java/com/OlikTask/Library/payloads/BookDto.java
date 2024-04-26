package com.OlikTask.Library.payloads;

import java.time.Year;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class BookDto {

	@NotBlank(message = "Title is required")
    @NotNull(message = "title can't be null")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

	@NotBlank(message = "Author Name is required")
	@NotNull(message = "Author Name can't be null")
	@Size(max = 15, message = "Author Name must be less than 15 characters")
    private String author;

    @NotBlank(message = "ISBN is required")
    @NotNull(message = "ISBN can't be null")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "ISBN must be in the format xxx-xxxxxxxxxx")
    private String isbn;

    @NotNull(message = "Publication year is required")
    @NotBlank(message = "Publication year can't be blank")
    private Year publicationYear;

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

	@Override
	public String toString() {
		return "BookDto [title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
				+ publicationYear + "]";
	}

	public BookDto(
			@NotBlank(message = "Title is required") @NotNull(message = "title can't be null") @Size(max = 100, message = "Title must be less than 100 characters") String title,
			@NotBlank(message = "Author Name is required") @NotNull(message = "Author Name can't be null") @Size(max = 15, message = "Author Name must be less than 15 characters") String author,
			@NotBlank(message = "ISBN is required") @NotNull(message = "ISBN can't be null") @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "ISBN must be in the format xxx-xxxxxxxxxx") String isbn,
			@NotNull(message = "Publication year is required") @NotBlank(message = "Publication year can't be blank") Year publicationYear) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
	}

	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
