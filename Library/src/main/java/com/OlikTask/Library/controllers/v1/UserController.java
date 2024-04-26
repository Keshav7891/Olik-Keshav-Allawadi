package com.OlikTask.Library.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OlikTask.Library.payloads.BookResponse;
import com.OlikTask.Library.payloads.UserDto;
import com.OlikTask.Library.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "CRUD REST API For User")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	@Operation(summary = "Create A User")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto savedUser = this.userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.OK).body(savedUser);
	}
	
	@GetMapping("/dues/{username}")
	@Operation(summary = "Get All The Books Issued By A User And Yet To Be Returned")
	public ResponseEntity<BookResponse> userDues(@PathVariable String username){
		BookResponse allDueBoookResponse = this.userService.getUserDues(username);
		return ResponseEntity.status(HttpStatus.OK).body(allDueBoookResponse);
	}
}
