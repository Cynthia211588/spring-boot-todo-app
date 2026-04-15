package co.sohamds.spring.todo.domain;

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
        Todo todo = new Todo(1L, "Study Java", "Yes");
        assertEquals(1L, todo.getId());
        assertEquals("Study Java", todo.getTodoItem());
        assertEquals("Yes", todo.getCompleted());
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
        Todo todo1 = new Todo(1L, "Task A", "No");
        Todo todo2 = new Todo(1L, "Task A", "No");
        assertEquals(todo1, todo2);
    }

    @Test
    void testTodoNotEqual() {
        Todo todo1 = new Todo(1L, "Task A", "No");
        Todo todo2 = new Todo(2L, "Task B", "Yes");
        assertNotEquals(todo1, todo2);
    }

    @Test
    void testTodoToString() {
        Todo todo = new Todo(1L, "Test task", "No");
        String toString = todo.toString();
        assertTrue(toString.contains("Test task"));
        assertTrue(toString.contains("No"));
    }
}
