package com.OlikTask.Library.services;

import com.OlikTask.Library.payloads.BookResponse;
import com.OlikTask.Library.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	BookResponse getUserDues(String username);
	
}
