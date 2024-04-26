package com.OlikTask.Library.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OlikTask.Library.models.User;

public interface UserRepo extends JpaRepository<User, UUID>{

	Optional<User> findByUsername(String username);
	
	@Query(value = "WITH data1 AS ( SELECT * FROM books WHERE id in (SELECT DISTINCT(book_id) FROM rentals WHERE return_date + INTERVAL '14 days ' < CURRENT_DATE AND status = 'RENTED' AND renter_id = ?1)) SELECT * FROM data1;", nativeQuery = true)
	List<Object[]> getUserDues(UUID userId);

	Boolean existsByUsername(String username);
	
}
