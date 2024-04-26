package com.OlikTask.Library.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OlikTask.Library.payloads.AuthorDto;
import com.OlikTask.Library.payloads.AuthorResponse;
import com.OlikTask.Library.services.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/authors")
@Tag(name = "CRUD REST API For Authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	//Create Author
	@PostMapping("/")
	@Operation(summary = "Create Author")
	public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
	    AuthorDto savedAuthorDto = this.authorService.createAuthor(authorDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthorDto);
	}
	
	//Read Author
	@GetMapping("/")
	@Operation(summary = "Get All Authors")
	public ResponseEntity<AuthorResponse> getAllAuthors(){
	    AuthorResponse allAuthorsDtoResponse = this.authorService.getAllAuthors();
	    return ResponseEntity.status(HttpStatus.OK).body(allAuthorsDtoResponse);
	}
	
	@GetMapping("/{name}/")
	@Operation(summary = "Get Author By Name")
	public ResponseEntity<AuthorDto> getAuthor(@PathVariable String name){
	    AuthorDto selectedAuthorDto = this.authorService.getAuthor(name);
	    return ResponseEntity.status(HttpStatus.CREATED).body(selectedAuthorDto);
	}
	
	// Update Author
	@PutMapping("/{name}/")
	@Operation(summary = "Update Author")
	public ResponseEntity<AuthorDto> updateAuthor(@Valid @RequestBody AuthorDto authorDto , @PathVariable String name){
	    AuthorDto updatedAuthorDto = this.authorService.updateAuthor(authorDto,name);
	    return ResponseEntity.status(HttpStatus.OK).body(updatedAuthorDto);
	}
	
	//Delete Author
	@DeleteMapping(value = "/{name}/")
	@Operation(summary = "Delete Author By Name")
	public String deleteAuthor(@PathVariable String name){
	    this.authorService.deleteAuthor(name);
	    return "Author Deleted";
	}


}
