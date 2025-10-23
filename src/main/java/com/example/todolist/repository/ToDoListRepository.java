package com.example.todolist.repository;

import com.example.todolist.model.ToDoListModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListModel, Long> {
    
    boolean existsByTitle(String title);
    
    // Retorna a tarefa se o título existir (necessário para o PUT)
    Optional<ToDoListModel> findByTitle(String title);
}
