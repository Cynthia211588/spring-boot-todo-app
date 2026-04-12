package co.sohamds.spring.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.sohamds.spring.todo.domain.Todo;
import co.sohamds.spring.todo.repository.TodoRepository;

@Controller
public class TodoController {
	private static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping
	public String index() {
	return "index.html";
}

@GetMapping("/todos")
public String todos(Model model) {
model.addAttribute("todos", todoRepository.findAll());
log.info("Todo list loaded");
return "todos";
}

@PostMapping("/todoNew")
public String add(@RequestParam String todoItem, @RequestParam
	String status, Model model) {
	Todo todo = new Todo(todoItem, status);
	todo.setTodoItem(todoItem);
	todo.setCompleted(status);
	todoRepository.save(todo);
	model.addAttribute("todos", todoRepository.findAll());
	log.info("Todo added: {}", todoItem);
	return "redirect:/todos";
}

@PostMapping("/todoDelete/{id}")
public String delete(@PathVariable long id, Model model) {
	todoRepository.deleteById(id);
	model.addAttribute("todos", todoRepository.findAll());
	log.info("Todo deleted: id={}", id);
	return "redirect:/todos"; 
}

@PostMapping("/todoUpdate/{id}")
public String update(@PathVariable long id, Model model) {
	Todo todo = todoRepository.findById(id).get();
	if("Yes".equals(todo.getCompleted())) {
	todo.setCompleted("No");
	}
	else {
	todo.setCompleted("Yes");
	}
	todoRepository.save(todo);
	model.addAttribute("todos", todoRepository.findAll());
	log.info("Todo status updated: id={}", id);
	return "redirect:/todos";
}
}