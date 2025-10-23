package com.example.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotação fará com que o Spring retorne automaticamente o status 409 (Conflict) 
// quando esta exceção for lançada.
@ResponseStatus(HttpStatus.CONFLICT)
public class TaskAlreadyExistsException extends RuntimeException {

    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}