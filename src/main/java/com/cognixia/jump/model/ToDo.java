package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // make sure our date loads fast enough w/o getting error

public class ToDo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "varchar(200) default 'N/A'")
	private String description;
	
	@NotNull
	@Column(columnDefinition = "varchar(5) default 'N/A'")
	private String completed;
	
	@NotNull 
	@Column(columnDefinition = "varchar(10) default 'N/A'")
	private String dueDate;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public ToDo() {
		this(-1L, "N/A", "N/A", "N/A");
	}
	
	public ToDo(Long id, @NotNull String description, @NotNull String completed, @NotNull String dueDate) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// only need setter for User
	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "ToDo [id=" + id + ", description=" + description + ", completed=" + completed + ", dueDate=" + dueDate
				+ "]";
	}
	
	
}
