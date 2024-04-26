package com.OlikTask.Library.services;

import com.OlikTask.Library.payloads.AuthorDto;
import com.OlikTask.Library.payloads.AuthorResponse;

public interface AuthorService {
	
	//Create Author
	public AuthorDto createAuthor(AuthorDto authorDto);
	
	//Read Author
	public AuthorResponse getAllAuthors();
	
	//public UUID getAuthorId(String name);
	
	public AuthorDto getAuthor(String name);
	
	// Update Author
	public AuthorDto updateAuthor(AuthorDto authorDto , String name);
	
	//Delete Author
	public void deleteAuthor(String name);
	
}

