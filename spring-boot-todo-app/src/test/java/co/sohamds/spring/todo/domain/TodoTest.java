package co.sohamds.spring.todo.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testCreateTodoWithConstructor() {
        Todo todo = new Todo("Buy groceries", "No");
        assertEquals("Buy groceries", todo.getTodoItem());
        assertEquals("No", todo.getCompleted());
    }

    @Test
    void testCreateTodoWithAllArgsConstructor() {
        LocalDate createdDate = LocalDate.of(2026, 4, 18);
        Todo todo = new Todo("Study Java", "Yes", createdDate);
        assertEquals("Study Java", todo.getTodoItem());
        assertEquals("Yes", todo.getCompleted());
        assertEquals(createdDate, todo.getCreatedDate());
    }

    @Test
    void testCreateTodoWithNoArgsConstructor() {
        Todo todo = new Todo();
        assertNull(todo.getTodoItem());
        assertNull(todo.getCompleted());
    }

    @Test
    void testSetAndGetTodoItem() {
        Todo todo = new Todo();
        todo.setTodoItem("Clean house");
        assertEquals("Clean house", todo.getTodoItem());
    }

    @Test
    void testSetAndGetCompleted() {
        Todo todo = new Todo();
        todo.setCompleted("Yes");
        assertEquals("Yes", todo.getCompleted());
    }

    @Test
    void testSetAndGetId() {
        Todo todo = new Todo();
        todo.setId(10L);
        assertEquals(10L, todo.getId());
    }

    @Test
    void testTodoBuilder() {
        Todo todo = Todo.builder()
                .id(1L)
                .todoItem("Read book")
                .completed("No")
                .build();
        assertEquals(1L, todo.getId());
        assertEquals("Read book", todo.getTodoItem());
        assertEquals("No", todo.getCompleted());
    }

    @Test
    void testTodoEquality() {
        LocalDate createdDate = LocalDate.of(2026, 4, 18);
        Todo todo1 = new Todo("Task A", "No", createdDate);
        Todo todo2 = new Todo("Task A", "No", createdDate);
        assertEquals(todo1, todo2);
    }

    @Test
    void testTodoNotEqual() {
        Todo todo1 = new Todo("Task A", "No", LocalDate.of(2026, 4, 18));
        Todo todo2 = new Todo("Task B", "Yes", LocalDate.of(2026, 4, 19));
        assertNotEquals(todo1, todo2);
    }

    @Test
    void testTodoToString() {
        Todo todo = new Todo("Test task", "No", LocalDate.of(2026, 4, 18));
        String toString = todo.toString();
        assertTrue(toString.contains("Test task"));
        assertTrue(toString.contains("No"));
    }
}
