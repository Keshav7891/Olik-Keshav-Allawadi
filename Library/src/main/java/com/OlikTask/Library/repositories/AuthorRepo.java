package com.OlikTask.Library.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OlikTask.Library.models.Author;

public interface AuthorRepo extends JpaRepository<Author,UUID>{

    Optional<Author> findByName(String name);
    
    Boolean existsByName(String name);

}