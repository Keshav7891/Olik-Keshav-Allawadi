package com.OlikTask.Library.payloads;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RentalDto {

    @NotNull(message = "Book Title Is necessary")
    @NotBlank(message = "Book Title can't be blank")
    private String book;
    
    @NotNull(message = "Renter Username Is necessary")
    @NotBlank(message = "Renter Username can't be blank")
    private String renterUsername;

	@NotNull(message = "Rental date is required")
    @FutureOrPresent(message = "Rental date must be in the present or future")
    private LocalDate rentalDate;

    @NotNull(message = "Return date is required")
    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getRenterUsername() {
		return renterUsername;
	}

	public void setRenterUsername(String renterUsername) {
		this.renterUsername = renterUsername;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "RentalDto [book=" + book + ", renterUsername=" + renterUsername + ", rentalDate=" + rentalDate
				+ ", returnDate=" + returnDate + "]";
	}

	public RentalDto(
			@NotNull(message = "Book Title Is necessary") @NotBlank(message = "Book Title can't be blank") String book,
			@NotNull(message = "Renter Username Is necessary") @NotBlank(message = "Renter Username can't be blank") String renterUsername,
			@NotNull(message = "Rental date is required") @FutureOrPresent(message = "Rental date must be in the present or future") LocalDate rentalDate,
			@NotNull(message = "Return date is required") @Future(message = "Return date must be in the future") LocalDate returnDate) {
		super();
		this.book = book;
		this.renterUsername = renterUsername;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public RentalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

    
    
}
