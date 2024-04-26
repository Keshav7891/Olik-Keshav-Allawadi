package com.OlikTask.Library.services.IMPL;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OlikTask.Library.exceptions.LibraryAPIException;
import com.OlikTask.Library.exceptions.ResourceNotFoundException;
import com.OlikTask.Library.models.Author;
import com.OlikTask.Library.payloads.AuthorDto;
import com.OlikTask.Library.payloads.AuthorResponse;
import com.OlikTask.Library.repositories.AuthorRepo;
import com.OlikTask.Library.services.AuthorService;
import com.OlikTask.Library.utils.ModelMapper;

import jakarta.transaction.Transactional;

@Service
public class AuthorServiceIMPL implements AuthorService {

	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public AuthorDto createAuthor(AuthorDto authorDto) {
	    if(this.authorRepo.existsByName(authorDto.getName()) == false) {
	    	Author author = this.modelMapper.authorDtoToAuthor(authorDto);
	    	Author savedAuthor = this.authorRepo.save(author);
	    	return this.modelMapper.authorToAuthorDto(savedAuthor);
	    }else {
	    	throw new LibraryAPIException(HttpStatus.BAD_REQUEST, authorDto.getName() + " Already Exists");
	    }
	}
	
	@Override
	public AuthorResponse getAllAuthors() {
	    List<Author> allAuthors = this.authorRepo.findAll();
	    List <AuthorDto> allAuthorsDto = allAuthors.stream().map( (author) -> this.modelMapper.authorToAuthorDto(author) ).collect(Collectors.toList());
	    AuthorResponse allAuthorsResponse = new AuthorResponse();
	    allAuthorsResponse.setData(allAuthorsDto);
	    allAuthorsResponse.setTotal(allAuthorsDto.size());
	    return allAuthorsResponse;
	}
	
	@Override
	public AuthorDto getAuthor(String name) {
	    Optional<Author> authorOptional = this.authorRepo.findByName(name);
	    if (authorOptional.isPresent()) {
	        Author author = authorOptional.get();
	        return this.modelMapper.authorToAuthorDto(author);
	    }else{
	        throw new ResourceNotFoundException("Author"," Name ", name);
	    }
	}
	
	@Override
	@Transactional
	public AuthorDto updateAuthor(AuthorDto authorDto, String name) {
	    Optional<Author> authorOptional = this.authorRepo.findByName(name);
	    if (authorOptional.isPresent()) {
	        Author author = authorOptional.get();
	        author.setName(authorDto.getName());
	        author.setBiography(authorDto.getBiography());
	        Author savedAuthor = this.authorRepo.save(author);
	        return this.modelMapper.authorToAuthorDto(savedAuthor);
	    }else{
	        throw new ResourceNotFoundException("Author"," Name ", name);
	    }
	}
	
	@Override
	@Transactional
	public void deleteAuthor(String name) {
	    Optional<Author> authorOptional = this.authorRepo.findByName(name);
	    if (authorOptional.isPresent()) {
	        Author author = authorOptional.get();
	        this.authorRepo.deleteById(author.getId());
	    }else{
	        throw new ResourceNotFoundException("Author"," Name ", name);
	    }
	}
}
