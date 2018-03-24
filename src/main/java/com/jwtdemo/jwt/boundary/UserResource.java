package com.jwtdemo.jwt.boundary;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtdemo.jwt.domain.User;

@RestController
@RequestMapping("users")
public class UserResource {

	private final List<User> users = Arrays.asList(new User("Hadi"), new User("Dija"));

	@GetMapping
	public ResponseEntity<List<User>> getUsers(Principal principal) {
		// principal.getName with return username
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
