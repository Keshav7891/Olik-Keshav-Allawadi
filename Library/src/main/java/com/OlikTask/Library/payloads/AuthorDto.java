package com.OlikTask.Library.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthorDto {
	
	 @NotBlank(message = "Name is required")
	 @NotNull(message = "Name can't be null")
	 @Size(max = 15, message = "Name must be less than 15 characters")
	 private String name;
	 
	 @NotBlank(message = "Biography is required")
	 @Size(max = 200, message = "Biography must be less than 200 characters")
	 private String biography;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBiography() {
		return biography;
	}
	
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	@Override
	public String toString() {
		return "AuthorDto [name=" + name + ", biography=" + biography + "]";
	}
	
	public AuthorDto(
			@NotBlank(message = "Name is required") @NotNull(message = "Name can't be null") @Size(max = 15, message = "Name must be less than 15 characters") String name,
			@NotBlank(message = "Biography is required") @Size(max = 200, message = "Biography must be less than 200 characters") String biography) {
		super();
		this.name = name;
		this.biography = biography;
	}
	
	public AuthorDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
}



