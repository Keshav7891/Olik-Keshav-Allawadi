package com.OlikTask.Library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "API Health Check")
public class HealthCheckController {
	
	@GetMapping("api/online-status")
	@Operation(summary = "Check If API is Online Or Not")
	public String checkOnline() {
		return "API is Live";
	}
	
}
