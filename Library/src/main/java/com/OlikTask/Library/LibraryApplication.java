package com.OlikTask.Library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library API",description = "REST API For Olik Library Assignment", version = "v1"), externalDocs = @ExternalDocumentation(description = "Github Repository Linked" , url = "https://github.com/Keshav7891/Olik-Keshav-Allawadi"))
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
