package com.OlikTask.Library.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OlikTask.Library.models.Rental;


public interface RentalRepo extends JpaRepository<Rental, UUID>{

	@Query(value = "SELECT * FROM rentals WHERE book_id = ?1" , nativeQuery = true)
	List<Rental> getAllRentalsOfBook(UUID bookId);
	
	Boolean existsByBookId(UUID bookId);
	
}
