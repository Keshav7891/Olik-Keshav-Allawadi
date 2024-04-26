package com.OlikTask.Library.services.IMPL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OlikTask.Library.exceptions.LibraryAPIException;
import com.OlikTask.Library.exceptions.ResourceNotFoundException;
import com.OlikTask.Library.models.Book;
import com.OlikTask.Library.models.Rental;
import com.OlikTask.Library.models.User;
import com.OlikTask.Library.payloads.RentalDto;
import com.OlikTask.Library.payloads.RentalResponse;
import com.OlikTask.Library.repositories.BookRepo;
import com.OlikTask.Library.repositories.RentalRepo;
import com.OlikTask.Library.repositories.UserRepo;
import com.OlikTask.Library.services.RentalService;
import com.OlikTask.Library.utils.ModelMapper;

import jakarta.transaction.Transactional;

@Service
public class RentalServiceIMPL implements RentalService{

	//UUID id, UUID bookId, UUID renterId, LocalDate rentalDate, LocalDate returnDate, String status
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RentalRepo rentalRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	@Transactional
	public RentalDto rentBook(RentalDto rentalDto) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(rentalDto.getBook());
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			if(book.getRentalId() == null) {
				Optional<User> userOptional = this.userRepo.findByUsername(rentalDto.getRenterUsername());
				if(userOptional.isPresent()) {
					User user = userOptional.get();
					Rental rental = new Rental();
					rental.setBookId(book.getId());
					rental.setRentalDate(LocalDate.now());
					rental.setReturnDate(rentalDto.getReturnDate());
					rental.setStatus("RENTED");
					rental.setRenterId(user.getId());
					Rental rentedRental = this.rentalRepo.save(rental);
					book.setRentalId(rentedRental.getId());
					this.bookRepo.save(book);
					return this.modelMapper.rentalToRentalDto(rentedRental);
				}else {
					throw new ResourceNotFoundException("USER", "Username", rentalDto.getRenterUsername());
				}
			}else {
				throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book Already Rented");
			}
		}else {
			throw new ResourceNotFoundException("Book", "Title", rentalDto.getBook());
		}
	}

	@Override
	@Transactional
	public RentalDto returnBook(String title) {
		Book book = this.bookRepo.findByTitle(title).orElseThrow( ()->  new ResourceNotFoundException("Book", "Title", title));
		if(book.getRentalId() == null) {
			if(this.rentalRepo.existsByBookId(book.getId())) {
				throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book Already Returned");
			}
			throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book was Never Issued");
		}
		Rental rental = this.rentalRepo.findById(book.getRentalId()).orElseThrow( ()-> new ResourceNotFoundException("Rental", "ID", book.getRentalId().toString()) );
		if(rental.getStatus().equals("RENTED")) {
			rental.setStatus("RETURNED");
			rental.setReturnDate(LocalDate.now());
			book.setRentalId(null);
			Rental returnedRental = this.rentalRepo.save(rental);
			this.bookRepo.save(book);
			return this.modelMapper.rentalToRentalDto(returnedRental);
		}else {
			throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book Was Never Issued");
		}
	}

	@Override
	public RentalResponse getAllRentals() {
		List<Rental> allRentals = this.rentalRepo.findAll();
		List<RentalDto> allRentalsDto = allRentals.stream().map( (rental) -> this.modelMapper.rentalToRentalDto(rental) ).collect(Collectors.toList());
		RentalResponse rentalResponse = new RentalResponse();
		rentalResponse.setData(allRentalsDto);
		rentalResponse.setTotal(allRentalsDto.size());
		return rentalResponse;
	}

	@Override
	public RentalResponse getAllRentalsOfBook(String title) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(title);
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			List<Rental> allRentalsOfBook = this.rentalRepo.getAllRentalsOfBook(book.getId());
			List<RentalDto> allRentalsDtoOfBook = allRentalsOfBook.stream().map( (rental) -> this.modelMapper.rentalToRentalDto(rental) ).collect(Collectors.toList());
			RentalResponse rentalResponse = new RentalResponse();
			rentalResponse.setData(allRentalsDtoOfBook);
			rentalResponse.setTotal(allRentalsDtoOfBook.size());
			return rentalResponse;
		}else {
			throw new ResourceNotFoundException("Book", "Title", title);
		}
	}

	@Override
	public RentalDto getActiveRentalOfBook(String title) {
		Optional<Book> bookOptional = this.bookRepo.findByTitle(title);
		if(bookOptional.isPresent()) {
			Book book = bookOptional.get();
			if(book.getRentalId() == null) {
				if(this.rentalRepo.existsByBookId(book.getId())) {
					throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "No Active Book Rental transaction Found");
				}
				throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book was Never Issued");
			}
			Optional<Rental> activeRentalOfBookOptional = this.rentalRepo.findById(book.getRentalId());
			if(activeRentalOfBookOptional.isPresent()) {
				Rental activeRentalOfBook = activeRentalOfBookOptional.get();
				return this.modelMapper.rentalToRentalDto(activeRentalOfBook);
			}else {
				throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Book Was Never Rented");
			}
		}else {
			throw new ResourceNotFoundException("Book", "Title", title);
		}
	}
}
