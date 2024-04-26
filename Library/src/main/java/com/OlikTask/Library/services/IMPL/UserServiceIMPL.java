package com.OlikTask.Library.services.IMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.OlikTask.Library.exceptions.LibraryAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.OlikTask.Library.exceptions.ResourceNotFoundException;
import com.OlikTask.Library.models.Book;
import com.OlikTask.Library.models.User;
import com.OlikTask.Library.payloads.BookDto;
import com.OlikTask.Library.payloads.BookResponse;
import com.OlikTask.Library.payloads.UserDto;
import com.OlikTask.Library.repositories.BookRepo;
import com.OlikTask.Library.repositories.UserRepo;
import com.OlikTask.Library.services.UserService;
import com.OlikTask.Library.utils.ModelMapper;

import jakarta.transaction.Transactional;

@Service
public class UserServiceIMPL implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookRepo bookRepo;
	
	
	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {
		if(!this.userRepo.existsByUsername(userDto.getUsername())) {
			User user = this.modelMapper.userDtoToUser(userDto);
			User savedUser = this.userRepo.save(user);
			return this.modelMapper.userToUserDto(savedUser);
		}else{
			throw new LibraryAPIException(HttpStatus.BAD_REQUEST, userDto.getUsername() + " Already Exists");
		}
	}

	@Override
	public BookResponse getUserDues(String username) {
		Optional<User> userOptional = this.userRepo.findByUsername(username);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			List<BookDto> allDueBookDto = new ArrayList<>();
			List<Object[]> res = this.userRepo.getUserDues(user.getId());
			for (Object[] row : res) {
			    UUID bookId = (UUID) row[2];
			    Book book = this.bookRepo.findById(bookId).get();
			    allDueBookDto.add(this.modelMapper.bookToBookDto(book));
			}
			BookResponse bookResponse = new BookResponse();
			bookResponse.setData(allDueBookDto);
			bookResponse.setTotal(allDueBookDto.size());
			return bookResponse;
		}else {
			throw new ResourceNotFoundException("User", "Username", username);
		}
	}

	
}
