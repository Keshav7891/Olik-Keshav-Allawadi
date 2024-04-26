package com.OlikTask.Library.services.IMPL;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OlikTask.Library.exceptions.LibraryAPIException;
import com.OlikTask.Library.exceptions.ResourceNotFoundException;
import com.OlikTask.Library.models.Author;
import com.OlikTask.Library.models.Book;
import com.OlikTask.Library.payloads.BookDto;
import com.OlikTask.Library.payloads.BookResponse;
import com.OlikTask.Library.repositories.AuthorRepo;
import com.OlikTask.Library.repositories.BookRepo;
import com.OlikTask.Library.services.BookService;
import com.OlikTask.Library.utils.ModelMapper;

import jakarta.transaction.Transactional;


@Service
public class BookServiceIMPL implements BookService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookRepo bookRepo; 
	
	@Autowired
	private AuthorRepo authorRepo;
	
	@Override
	@Transactional
	public BookDto createBook(BookDto bookDto) {
		if(this.bookRepo.existsByTitle(bookDto.getTitle()) == false) {
			Book book = this.modelMapper.bookDtoToBook(bookDto);
			Book savedBook = this.bookRepo.save(book);
			return this.modelMapper.bookToBookDto(savedBook);
		}else {
        	throw new LibraryAPIException(HttpStatus.BAD_REQUEST, bookDto.getTitle() + " Already Exists");
		}		
	}

	@Override
	public BookResponse getAllBooks() {
		List<Book> allBooks = this.bookRepo.findAll();
		List<BookDto> allBooksDto = allBooks.stream().map( (book) -> this.modelMapper.bookToBookDto(book) ).collect(Collectors.toList());
		BookResponse bookResponse = new BookResponse();
		bookResponse.setData(allBooksDto);
		bookResponse.setTotal(allBooksDto.size());
		return bookResponse;
	}

	@Override
	public BookDto getBook(String title) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(title);
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			return this.modelMapper.bookToBookDto(book);
		}else {
			throw new ResourceNotFoundException("Book", "title", title);
		}
	}

	@Override
	@Transactional
	public BookDto updateBook(BookDto bookDto, String title) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(title);
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			Optional<Author> authorOptional = this.authorRepo.findByName(bookDto.getAuthor());
			if(authorOptional.isPresent()) {
				Author author = authorOptional.get();
				book.setAuthorId(author.getId());
				book.setIsbn(bookDto.getIsbn());
				book.setPublicationYear(bookDto.getPublicationYear());
				book.setTitle(bookDto.getTitle());
				Book savedBook = this.bookRepo.save(book);
				return this.modelMapper.bookToBookDto(savedBook);
			}else {
				throw new ResourceNotFoundException("Author", "Name", bookDto.getAuthor());
			}
		}else {
			throw new ResourceNotFoundException("Book", "title", bookDto.getTitle());
		}
	}

	@Override
	@Transactional
	public void deleteBook(String title) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(title);
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			this.bookRepo.deleteById(book.getId());
		}else {
			throw new ResourceNotFoundException("Book", "Title", title);
		}
	}

	@Override
	public BookResponse getBooksByAuthor(String name) {
		Optional<Author> authorOptional = this.authorRepo.findByName(name);
		if(authorOptional.isPresent()) {
			Author author = authorOptional.get();
			List<Book> booksByAuthor = this.bookRepo.getBooksByAuthor(author.getId());
			List<BookDto> booksDtoByAuthor = booksByAuthor.stream().map( (book) -> this.modelMapper.bookToBookDto(book) ).collect(Collectors.toList());
			BookResponse bookResponse = new BookResponse();
			bookResponse.setData(booksDtoByAuthor);
			bookResponse.setTotal(booksDtoByAuthor.size());
			return bookResponse;
		}else {
			throw new ResourceNotFoundException("Author", "Name", name);
		}
	}

	@Override
	public BookResponse getBooksAvailableToRent() {
		List<Book> availableToRentBooks = this.bookRepo.getBooksAvailableToRent();
		List<BookDto> availableToRentBooksDto = availableToRentBooks.stream().map( (book) -> this.modelMapper.bookToBookDto(book) ).collect(Collectors.toList());
		BookResponse bookResponse = new BookResponse();
		bookResponse.setData(availableToRentBooksDto);
		bookResponse.setTotal(availableToRentBooksDto.size());
		return bookResponse;
	}

	@Override
	public BookResponse getRentedBooks() {
		List<Book> rentedBooks = this.bookRepo.getRentedBooks();
		List<BookDto> rentedBooksDto = rentedBooks.stream().map( (book) -> this.modelMapper.bookToBookDto(book) ).collect(Collectors.toList());
		BookResponse bookResponse = new BookResponse();
		bookResponse.setData(rentedBooksDto);
		bookResponse.setTotal(rentedBooksDto.size());
		return bookResponse;
	}
}
