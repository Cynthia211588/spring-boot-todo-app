package co.sohamds.spring.todo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Todo {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String todoItem;
private String completed;
private LocalDate createdDate;
public Todo(String todoItem, String completed) {
	super();
	this.todoItem = todoItem;
	this.completed = completed;
	this.createdDate = LocalDate.now();
}
public Todo(String todoItem, String completed, LocalDate createdDate) {
	super();
	this.todoItem = todoItem;
	this.completed = completed;
	this.createdDate = createdDate;
}



}