package com.OlikTask.Library.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OlikTask.Library.exceptions.ResourceNotFoundException;
import com.OlikTask.Library.models.Author;
import com.OlikTask.Library.models.Book;
import com.OlikTask.Library.models.Rental;
import com.OlikTask.Library.models.User;
import com.OlikTask.Library.payloads.AuthorDto;
import com.OlikTask.Library.payloads.BookDto;
import com.OlikTask.Library.payloads.RentalDto;
import com.OlikTask.Library.payloads.UserDto;
import com.OlikTask.Library.repositories.AuthorRepo;
import com.OlikTask.Library.repositories.BookRepo;
import com.OlikTask.Library.repositories.UserRepo;

@Component
public class ModelMapper {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;
    
    @Autowired
    private UserRepo userRepo;

    public AuthorDto authorToAuthorDto (Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setBiography(author.getBiography());
        return authorDto;
    }

    public Author authorDtoToAuthor(AuthorDto authorDto){
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setBiography(authorDto.getBiography());
        return author;
    }
    
    public UserDto userToUserDto (User user){
    	UserDto userDto = new UserDto();
    	userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User userDtoToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        return user;
    }

    public BookDto bookToBookDto (Book book){
        BookDto bookDto = new BookDto();
        Optional<Author> authorOptional = authorRepo.findById(book.getAuthorId());
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            bookDto.setAuthor(author.getName());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setTitle(book.getTitle());
            bookDto.setPublicationYear(book.getPublicationYear());
            return bookDto;
        }else{
            throw new  ResourceNotFoundException("Author","ID", book.getAuthorId().toString());
        }
    }

    public Book bookDtoToBook(BookDto bookDto){
        Book book = new Book();
        Optional<Author> authorOptional = authorRepo.findByName(bookDto.getAuthor());
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            book.setAuthorId(author.getId());
            book.setIsbn(bookDto.getIsbn());
            book.setTitle(bookDto.getTitle());
            book.setPublicationYear(bookDto.getPublicationYear());
            return book;
        }else{
            throw new ResourceNotFoundException("Author","Name", bookDto.getAuthor());
        }
    }

    public Rental rentalDtoToRental(RentalDto rentalDto){
        Rental rental = new Rental();
        Optional<Book> bookOptional = bookRepo.findByTitle(rentalDto.getBook());
        if(bookOptional.isPresent()) {
        	Optional<User> userOptional = this.userRepo.findByUsername(rentalDto.getRenterUsername());
        	if(userOptional.isPresent()) {
        		Book book = bookOptional.get();
        		User user = userOptional.get();
                rental.setRentalDate(rentalDto.getRentalDate());
                rental.setBookId(book.getId());
                rental.setReturnDate(rentalDto.getReturnDate());
                rental.setRenterId(user.getId());
                return rental;
        	}else {
        		throw new ResourceNotFoundException("User", "Username", rentalDto.getRenterUsername());
        	}
        }else{
            throw new  ResourceNotFoundException("Book","Title", rentalDto.getBook());
        }
    }

    public RentalDto rentalToRentalDto(Rental rental){
        RentalDto rentalDto = new RentalDto();
        Optional<Book> bookOptional = bookRepo.findById(rental.getBookId());
        if(bookOptional.isPresent()){
        	Optional<User> userOptional = this.userRepo.findById(rental.getRenterId());
        	if(userOptional.isPresent()) {
        		Book book = bookOptional.get();
        		User user = userOptional.get();
                rentalDto.setBook(book.getTitle());
                rentalDto.setRentalDate(rental.getRentalDate());
                rentalDto.setReturnDate(rental.getReturnDate());
                rentalDto.setRenterUsername(user.getUsername());
                return rentalDto;
        	}else {
        		throw new ResourceNotFoundException("User", "ID", rental.getRenterId().toString());
        	}
        }else{
            throw new ResourceNotFoundException("Book","ID", rental.getId().toString());
        }
    }

}
