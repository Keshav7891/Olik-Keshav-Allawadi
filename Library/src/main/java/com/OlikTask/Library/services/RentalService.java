package com.OlikTask.Library.services;

import com.OlikTask.Library.payloads.RentalDto;
import com.OlikTask.Library.payloads.RentalResponse;

public interface RentalService {
		
	//Rent A Book || Create Rental Transaction
	public RentalDto rentBook(RentalDto rentalDto);
	
	//Return A Book || Update Rental Transaction
	public RentalDto returnBook(String title);
	
	//Read Rental Transaction
	public RentalResponse getAllRentals();
	
	public RentalResponse getAllRentalsOfBook(String title);
	
	public RentalDto getActiveRentalOfBook(String title);
	
}
