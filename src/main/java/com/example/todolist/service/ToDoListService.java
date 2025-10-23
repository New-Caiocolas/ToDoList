package com.example.todolist.service;

import com.example.todolist.exception.TaskAlreadyExistsException; // Importe a exceção
import com.example.todolist.model.ToDoListModel;
import com.example.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    public List<ToDoListModel> findAll() {
        return toDoListRepository.findAll();
    }

    public Optional<ToDoListModel> findById(Long id) {
        return toDoListRepository.findById(id);
    }

    public ToDoListModel save(ToDoListModel toDoListModel) {
        
        // 🚨 VALIDAÇÃO DE UNICIDADE 🚨
        if (toDoListRepository.existsByTitle(toDoListModel.getTitle())) {
            
            // Tratamento especial para o PUT/Atualização:
            // Se o ID for null (POST), é um novo registro, então lançamos a exceção.
            // Se o ID NÃO for null (PUT), precisamos verificar se a tarefa encontrada
            // é a tarefa que estamos tentando atualizar.
            if (toDoListModel.getId() == null) {
                 throw new TaskAlreadyExistsException("Já existe uma tarefa com o título: " + toDoListModel.getTitle());
            }
            
            // Para o PUT, se o título já existir, buscamos a tarefa existente.
            // Se a tarefa existente for DIFERENTE da que estamos atualizando, 
            // significa que estamos tentando salvar o título de OUTRA tarefa.
            Optional<ToDoListModel> existingTaskWithTitle = toDoListRepository.findByTitle(toDoListModel.getTitle());

            if (existingTaskWithTitle.isPresent() && !existingTaskWithTitle.get().getId().equals(toDoListModel.getId())) {
                 throw new TaskAlreadyExistsException("Já existe outra tarefa com o título: " + toDoListModel.getTitle());
            }
        }
        
        return toDoListRepository.save(toDoListModel);
    }
    
    // ... restante dos métodos (delete, toggleTaskCompletion) ...

    public void delete(Long id) {
        toDoListRepository.deleteById(id);
    }
    
    public Optional<ToDoListModel> toggleTaskCompletion(Long id) {
        return findById(id).map(task -> {
            // task.setCompleted(!task.isCompleted()); 
            return save(task);
        });
    }
}