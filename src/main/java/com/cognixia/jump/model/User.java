package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // make sure our date loads fast enough w/o getting error
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	@Column(columnDefinition = "varchar(20) default 'N/A'")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ToDo> todos;

	public User() {
		this(-1L, "N/A", "N/A", new ArrayList<ToDo>());
	}
	public User(Long id, @NotBlank String userName, @NotBlank String password, List<ToDo> todos) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.todos = todos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<ToDo> getTodos() {
		return todos;
	}
	
	public void setNewTodos() {
		
		for(ToDo a : todos) {
			a.setId(-1L);
		}
	}

	public void setTodos(List<ToDo> todos) {
		// this will override all your todos
		//this.todos = todos;
		
		for(int todo = 0; todo < todos.size(); todo++) {
			addTodo(todos.get(todo));
		}
	}
	
	public void addTodo(ToDo todo) {
		todo.setUser(this);
		todos.add(todo);
	}
	
	public ToDo getTodo(Long id) {
		
		for(int a = 0; a < todos.size(); a++) {
			
			if(todos.get(a).getId() == id) {
				return todos.get(a);
			}
		}
		
		return new ToDo();
	}
	
	public void updateTodo(ToDo todo) {
		
		ToDo toUpdate = getTodo(todo.getId());
		
		if(toUpdate.getId() != -1) {
			
			toUpdate.setDescription(todo.getDescription());
			toUpdate.setCompleted(todo.getCompleted());
			toUpdate.setDueDate(todo.getDueDate());
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
	
}
