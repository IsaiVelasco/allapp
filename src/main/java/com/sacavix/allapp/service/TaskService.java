package com.sacavix.allapp.service;

import com.sacavix.allapp.exceptions.ToDoExceptions;
import com.sacavix.allapp.mapper.TaskInDtoToTask;
import com.sacavix.allapp.persistence.entity.Task;
import com.sacavix.allapp.persistence.entity.TaskStatus;
import com.sacavix.allapp.persistence.repository.TaskRepository;
import com.sacavix.allapp.service.dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDtoToTask mapper;
    public TaskService(TaskRepository repository, TaskInDtoToTask mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDto taskInDto){
        Task task = mapper.amp(taskInDto);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }
    //hace un Query para obtener Task por estados
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
        return this.repository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinish(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskAsFinished(id);
    }
    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);
    }
}
