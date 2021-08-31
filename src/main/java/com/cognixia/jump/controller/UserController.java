package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ToDoRepository;
import com.cognixia.jump.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class UserController {
	@Autowired
	UserRepository repo;
	
	@GetMapping("/users")
	public List<User> getTodos(){
		return repo.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getTodo(@PathVariable long id) throws ResourceNotFoundException {
		if(repo.existsById(id)) {
			User todo = repo.getById(id);
			return ResponseEntity.ok().body(todo);
		}
		
		throw new ResourceNotFoundException("User with id = " + id + " is not found");
		
	}
	
	// Create a new user
	@PostMapping("/users")
	public ResponseEntity<User> addTodo(@Valid @RequestBody User user){
		user.setId(-1L);
		User created = repo.save(user);
		return ResponseEntity.status(201).body(created);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable long id) throws ResourceNotFoundException{
		if(repo.existsById(id)) {
			User removed = repo.getById(id);
			repo.deleteById(id);
			return new ResponseEntity<>(removed, HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("User with id = " + id + " is not found");
		
	}
	
	// Update username
	@PutMapping("/users/username")
	public ResponseEntity<?> updateUsernamebyId(@Valid @RequestBody User user) throws ResourceNotFoundException{
		Long passedId = user.getId();
		
		if(repo.existsById(passedId)) {
			User toUpdate = repo.getById(passedId);
			toUpdate.setUserName(user.getUserName());
			repo.save(toUpdate);
			return new ResponseEntity<>(toUpdate, HttpStatus.OK);		}
		
		throw new ResourceNotFoundException("User with id = " + passedId + " is not found");
	}
	
	// Update password
	@PutMapping("/users/password")
	public ResponseEntity<?> updatePasswordbyId(@Valid @RequestBody User user) throws ResourceNotFoundException{
		Long passedId = user.getId();
		
		if(repo.existsById(passedId)) {
			User toUpdate = repo.getById(passedId);
			toUpdate.setPassword(user.getPassword());
			repo.save(toUpdate);
			return new ResponseEntity<>(toUpdate, HttpStatus.OK);		}
		
		throw new ResourceNotFoundException("User with id = " + passedId + " is not found");
	}
	
	// Get user by username and password
	@GetMapping("/users/retrieveUser")
	public List<User> findByUsernameAndPsw(@PathParam(value = "userName") String userName, @PathParam(value = "password") String password)
	{
		return repo.findByUsernameAndPsw(userName, password);
	}

}
