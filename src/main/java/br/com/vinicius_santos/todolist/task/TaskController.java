package br.com.vinicius_santos.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody TaskModel task) {
        var createdTask = this.taskRepository.save(task);
        return ResponseEntity.status(201).body(createdTask);
    }

}
