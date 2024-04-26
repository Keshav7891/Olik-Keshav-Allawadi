package com.OlikTask.Library.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OlikTask.Library.payloads.BookDto;
import com.OlikTask.Library.payloads.BookResponse;
import com.OlikTask.Library.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/books")
@Tag(name = "CRUD REST API For Books")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	//Create Book
	@PostMapping("/")
	@Operation(summary = "Create Book")
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
		BookDto savedBookDto = this.bookService.createBook(bookDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBookDto);
	}
	
	//Read Book
	@GetMapping("/")
	@Operation(summary = "Get All Books")
	public ResponseEntity<BookResponse> getAllBooks(){
		BookResponse allBooksDto = this.bookService.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(allBooksDto);
	}
	
	@GetMapping("/{title}/")
	@Operation(summary = "Get Book By Title")
	public ResponseEntity<BookDto> getBook(@PathVariable String title) {
		BookDto selectedBookDto = this.bookService.getBook(title);
		return ResponseEntity.status(HttpStatus.OK).body(selectedBookDto);
	}
	
	// Update Book
	@PutMapping("/{title}/")
	@Operation(summary = "Update Book")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto ,@PathVariable String title){
		BookDto updatedBookDto = this.bookService.updateBook(bookDto, title);
		return ResponseEntity.status(HttpStatus.OK).body(updatedBookDto);
	}
	
	//Delete Book
	@DeleteMapping("/{title}/")
	@Operation(summary = "Delete Book by Title")
	public String deleteBook(@PathVariable String title){
		this.bookService.deleteBook(title);
		return "Book Deleted Successfully";
	}
	
	//Retrieve Books By Author
	@GetMapping("/author/{name}/")
	@Operation(summary = "Get All Books Of An Author")
	public ResponseEntity<BookResponse> getBooksByAuthor(@PathVariable String name){
		BookResponse allBooksDtoByAuthor = this.bookService.getBooksByAuthor(name);
		return ResponseEntity.status(HttpStatus.OK).body(allBooksDtoByAuthor);
	}
	
	//Retrieve Books Available To Rent
	@GetMapping("/available/")
	@Operation(summary = "get All Books Available To Rent")
	public ResponseEntity<BookResponse> getBooksAvailableToRent(){
		BookResponse booksDtoAvailableToRent = this.bookService.getBooksAvailableToRent();
		return ResponseEntity.status(HttpStatus.OK).body(booksDtoAvailableToRent);
	}
	
	//Retrieve Books Available To Rent
	@GetMapping("/rented/")
	@Operation(summary = "Get All Rented Books")
	public ResponseEntity<BookResponse> getRentedBooks(){
		BookResponse booksDtoRented = this.bookService.getRentedBooks();
		return ResponseEntity.status(HttpStatus.OK).body(booksDtoRented);
	}
	
	
	
}
