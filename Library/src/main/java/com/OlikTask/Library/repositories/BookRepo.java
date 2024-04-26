package com.OlikTask.Library.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.OlikTask.Library.models.Book;

public interface BookRepo extends JpaRepository<Book, UUID>{

    Optional<Book> findByTitle(String title);
    
    Boolean existsByTitle(String title);
    
    @Query(value = "SELECT * FROM books WHERE author_id = ?1", nativeQuery = true)
    List<Book> getBooksByAuthor(UUID authorId);

    @Query(value = "SELECT * FROM books WHERE rental_id IS NULL", nativeQuery = true)
    List<Book> getBooksAvailableToRent();
    
    @Query(value = "SELECT * FROM books WHERE rental_id IS NOT NULL", nativeQuery = true)
    List<Book> getRentedBooks();
}
