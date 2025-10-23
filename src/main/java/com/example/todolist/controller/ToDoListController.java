package com.example.todolist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.ToDoListModel; // A entidade correta
import com.example.todolist.service.ToDoListService;

@RestController
@RequestMapping("/api/tasks")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    // GET: Listar todas as tarefas (URL: /api/tasks)
    @GetMapping
    public List<ToDoListModel> getAllTasks() {
        return toDoListService.findAll();
    }

    // POST: Criar nova tarefa (URL: /api/tasks)
    @PostMapping
    public ToDoListModel createTask(@RequestBody ToDoListModel toDoListModel) {
        return toDoListService.save(toDoListModel);
    }

    // PUT: Atualizar uma tarefa existente (URL: /api/tasks/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ToDoListModel> updateTask(@PathVariable Long id, @RequestBody ToDoListModel toDoListModel) {
        return toDoListService.findById(id)
            .map(existingTask -> {
                existingTask.setTitle(toDoListModel.getTitle());
                existingTask.setDescription(toDoListModel.getDescription());
                // existingTask.setCompleted(toDoListModel.isCompleted());
                ToDoListModel updatedTask = toDoListService.save(existingTask);
                return ResponseEntity.ok(updatedTask);
            })
            .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    // PATCH: Marcar/Desmarcar como concluída (URL: /api/tasks/{id}/toggle)
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<ToDoListModel> toggleTask(@PathVariable Long id) {
        return toDoListService.toggleTaskCompletion(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // DELETE: Excluir tarefa (URL: /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (toDoListService.findById(id).isPresent()) {
            toDoListService.delete(id);
            return ResponseEntity.noContent().build(); // Retorna 204 (No Content) - Padrão para DELETE
        }
        return ResponseEntity.notFound().build(); 
    }
}

