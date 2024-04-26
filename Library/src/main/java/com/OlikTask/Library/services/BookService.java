package com.OlikTask.Library.services;

import com.OlikTask.Library.payloads.BookDto;
import com.OlikTask.Library.payloads.BookResponse;

public interface BookService {

	//Create Book
	public BookDto createBook(BookDto bookDto);
	
	//Read Book
	public BookResponse getAllBooks();
		
	public BookDto getBook(String title);
	
	// Update Book
	public BookDto updateBook(BookDto bookDto , String title);
	
	//Delete Book
	public void deleteBook(String title);
	
	//Retrieve Books By Author
	public BookResponse getBooksByAuthor(String name);
	
	//Retrieve Books Available To Rent
	public BookResponse getBooksAvailableToRent();
	
	//Retrieve Books Available To Rent
	public BookResponse getRentedBooks();
	
}
