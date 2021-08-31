package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ToDoRepository;
import com.cognixia.jump.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class ToDoController {
	
	@Autowired
	ToDoRepository todoRepo;
	
	@Autowired
	UserRepository userRepo;
	
	// Pull all todos for 1 user
	@GetMapping("/users/{userId}/todos")
	public List<ToDo> getAllToDosByUserId(@PathVariable (value = "userId") Long userId){
		return todoRepo.findByUserId(userId);
	}
	
	@GetMapping("/users/todos/{id}")
	public ResponseEntity<?> getTodo(@PathVariable long id) throws ResourceNotFoundException {
		if(todoRepo.existsById(id)) {
			ToDo todo = todoRepo.getById(id);
			return ResponseEntity.ok().body(todo);
		}
		
		throw new ResourceNotFoundException("Todo with id = " + id + " is not found");
		
	}
	
	// Create a new todo for a user
	@PostMapping("/users/{userId}/todos")
	public ResponseEntity<ToDo> addTodo(@PathVariable (value = "userId") Long userId,
					    @Valid @RequestBody ToDo todo) throws ResourceNotFoundException{
		todo.setId(-1L);
		if(userRepo.existsById(userId)) {
			Optional<User> user = userRepo.findById(userId);
			todo.setUser(user.get());
			ToDo created = todoRepo.save(todo);
			return ResponseEntity.status(201).body(created);
		}
		throw new ResourceNotFoundException("User with id = " + userId + " is not found");

	}
	
	// Remove a todo
	@DeleteMapping("/users/todos/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable long id) throws ResourceNotFoundException{
		if(todoRepo.existsById(id)) {
			ToDo removed = todoRepo.getById(id);
			todoRepo.deleteById(id);
			return new ResponseEntity<>(removed, HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("Todo with id = " + id + " is not found");
		
	}
	
	// Delete all todos for 1 user
	@DeleteMapping("/users/{userId}/todos")
	public ResponseEntity<?> deleteAllTodosForAUser(@PathVariable (value = "userId") Long userId) throws ResourceNotFoundException{
		if(userRepo.existsById(userId)) {
			List<ToDo> todosToRemoved = todoRepo.findByUserId(userId);
			for(ToDo todo : todosToRemoved) {
				todoRepo.delete(todo);
			}
			return new ResponseEntity<>(todosToRemoved, HttpStatus.OK);
		}
		
		throw new ResourceNotFoundException("User with id = " + userId + " is not found");
			
	}
	
	// Update all todos as completed for 1 user
	@PutMapping("/users/{userId}/todos")
	public ResponseEntity<?> updateAllTodosForAUser(@PathVariable (value = "userId") Long userId) throws ResourceNotFoundException{
		if(userRepo.existsById(userId)) {
			List<ToDo> todosToUpdated = todoRepo.findByUserId(userId);
			for(ToDo todo : todosToUpdated) {
				todo.setCompleted("Yes");
				todoRepo.save(todo);	
			}
			return new ResponseEntity<>(todosToUpdated, HttpStatus.OK);

	}

		throw new ResourceNotFoundException("User with id = " + userId + " is not found");

}
	// Update a todo from not completed to completed	
	@PutMapping("/todos/completed")
	public ResponseEntity<?> updateCompletedbyId(@Valid @RequestBody ToDo todo) throws ResourceNotFoundException{
		Long passedId = todo.getId();
		
		if(todoRepo.existsById(passedId)) {
			ToDo toUpdate = todoRepo.getById(passedId);
			toUpdate.setCompleted(todo.getCompleted());
			todoRepo.save(toUpdate);
			return new ResponseEntity<>(toUpdate, HttpStatus.OK);		}
		
		throw new ResourceNotFoundException("Todo with id = " + passedId + " is not found");
	}
	
	// Update todo's due date
	@PutMapping("/todos/dueDate")
	public ResponseEntity<?> updateDueDatebyId(@Valid @RequestBody ToDo todo) throws ResourceNotFoundException{
		Long passedId = todo.getId();
		
		if(todoRepo.existsById(passedId)) {
			ToDo toUpdate = todoRepo.getById(passedId);
			toUpdate.setDueDate(todo.getDueDate());
			todoRepo.save(toUpdate);
			return new ResponseEntity<>(toUpdate, HttpStatus.OK);		
		}
		
		throw new ResourceNotFoundException("Todo with id = " + passedId + " is not found");
	}
}
