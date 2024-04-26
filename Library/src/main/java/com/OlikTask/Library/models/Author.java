package com.OlikTask.Library.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	@Column(unique = true)
    private String name;
    
    private String biography;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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
		return "Author [id=" + id + ", name=" + name + ", biography=" + biography + "]";
	}

	public Author(UUID id, String name, String biography) {
		super();
		this.id = id;
		this.name = name;
		this.biography = biography;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

