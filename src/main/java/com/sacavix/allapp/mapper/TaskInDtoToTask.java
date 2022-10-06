package com.sacavix.allapp.mapper;

import com.sacavix.allapp.persistence.entity.Task;
import com.sacavix.allapp.persistence.entity.TaskStatus;
import com.sacavix.allapp.service.dto.TaskInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDtoToTask implements IMapper<TaskInDto, Task>{

    @Override
    public Task amp(TaskInDto in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
