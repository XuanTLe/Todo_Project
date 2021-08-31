package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	 List<ToDo> findByUserId(Long postId);
}
