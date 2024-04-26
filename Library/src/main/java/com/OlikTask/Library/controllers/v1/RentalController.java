package com.OlikTask.Library.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OlikTask.Library.payloads.RentalDto;
import com.OlikTask.Library.payloads.RentalResponse;
import com.OlikTask.Library.services.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/rentals")
@Tag(name = "CRUD REST API For Rental Transactions")
public class RentalController {
	
	@Autowired
	RentalService rentalService;
	
	//Rent A Book
	@PostMapping("/rent/")
	@Operation(summary = "Rent A Book")
	public ResponseEntity<RentalDto> rentBook(@RequestBody RentalDto rentalDto){
		RentalDto rentRentalDto = this.rentalService.rentBook(rentalDto);
		return ResponseEntity.status(HttpStatus.OK).body(rentRentalDto);
	}
	
	//Return A Book
	@PutMapping("/return/{title}/")
	@Operation(summary = "Return A Book")
	public ResponseEntity<RentalDto> returnBook(@PathVariable String title){
	    RentalDto returnRentalDto = this.rentalService.returnBook(title);
	    return ResponseEntity.status(HttpStatus.OK).body(returnRentalDto);
	}
	
	//Read Rental Transaction
	@GetMapping("/")
	@Operation(summary = "Get All Rental Transactions")
	public ResponseEntity<RentalResponse> getAllRentals(){
		RentalResponse allRentalsResponse = this.rentalService.getAllRentals();
		return ResponseEntity.status(HttpStatus.OK).body(allRentalsResponse);
	}
	
	@GetMapping("/all/{title}/")
	@Operation(summary = "Get All Rental Transactions Of A Book")
	public ResponseEntity<RentalResponse> getAllRentalsOfBook(@PathVariable String title) {
		RentalResponse allRentalsDtoOfBook = this.rentalService.getAllRentalsOfBook(title);
		return ResponseEntity.status(HttpStatus.OK).body(allRentalsDtoOfBook);
	}
	
	@GetMapping("/active/{title}/")
	@Operation(summary = "Get Active Rental Transaction Of A Book")
	public ResponseEntity<RentalDto> getActiveRentalOfBook(@PathVariable String title){
		RentalDto activeRentalDtoOfBook = this.rentalService.getActiveRentalOfBook(title);
		return ResponseEntity.status(HttpStatus.OK).body(activeRentalDtoOfBook);
	}
	
}
