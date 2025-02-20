package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TodoEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	TodoRepository todoRepository;
	
	public void createList(String title, String detail) {
		TodoEntity list = new TodoEntity(title,detail,false);
		todoRepository.save(list);
	}

	public List<TodoEntity> getList() {
		return todoRepository.findAll();
	}
	
	public void deleteList(Long id) {
		todoRepository.deleteById(id);
	}
	
	public TodoEntity updateList(Long id, TodoEntity todoEntity) {
		TodoEntity todo = todoRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("title", "id", id)
				);
		todo.setTitle(todoEntity.getTitle());
		todo.setDetail(todoEntity.getDetail());
		TodoEntity updateList = todoRepository.save(todo);
		return updateList;
	}
	
	public void test(Long id) {
		System.err.println("test");
	}
}
