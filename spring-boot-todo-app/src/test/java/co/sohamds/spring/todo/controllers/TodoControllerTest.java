package co.sohamds.spring.todo.controllers;

import co.sohamds.spring.todo.domain.Todo;
import co.sohamds.spring.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void testIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetTodos() throws Exception {
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(view().name("todos"))
                .andExpect(model().attributeExists("todos"));
    }

    @Test
    @WithMockUser
    void testAddTodo() throws Exception {
        mockMvc.perform(post("/todoNew")
                        .with(csrf())
                        .param("todoItem", "Test task")
                        .param("status", "No"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todos"));

        assertEquals(1, todoRepository.count());
    }

    @Test
    @WithMockUser
    void testDeleteTodo() throws Exception {
        Todo todo = new Todo("Delete me", "No");
        todo = todoRepository.save(todo);

        mockMvc.perform(post("/todoDelete/" + todo.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todos"));

        assertEquals(0, todoRepository.count());
    }

    @Test
    @WithMockUser
    void testUpdateTodoFromNoToYes() throws Exception {
        Todo todo = new Todo("Update me", "No");
        todo = todoRepository.save(todo);

        mockMvc.perform(post("/todoUpdate/" + todo.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todos"));

        Todo updated = todoRepository.findById(todo.getId()).get();
        assertEquals("Yes", updated.getCompleted());
    }

    @Test
    @WithMockUser
    void testUpdateTodoFromYesToNo() throws Exception {
        Todo todo = new Todo("Update me", "Yes");
        todo = todoRepository.save(todo);

        mockMvc.perform(post("/todoUpdate/" + todo.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todos"));

        Todo updated = todoRepository.findById(todo.getId()).get();
        assertEquals("No", updated.getCompleted());
    }

    @Test
    @WithMockUser
    void testAddMultipleTodos() throws Exception {
        mockMvc.perform(post("/todoNew")
                .with(csrf())
                .param("todoItem", "Task 1")
                .param("status", "No"));

        mockMvc.perform(post("/todoNew")
                .with(csrf())
                .param("todoItem", "Task 2")
                .param("status", "Yes"));

        assertEquals(2, todoRepository.count());
    }
}
