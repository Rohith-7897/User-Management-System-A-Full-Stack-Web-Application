package org.jsp.UserFullStack.controller;

import java.util.List;

import org.jsp.UserFullStack.exception.UserNotFoundException;
import org.jsp.UserFullStack.model.User;
import org.jsp.UserFullStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	@GetMapping("/users")
	List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable long id) {
		return userRepository.findById(id).map(user -> {
			user.setPhone(newUser.getPhone());
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id " + id + " has been deleted success.";
	}

}
