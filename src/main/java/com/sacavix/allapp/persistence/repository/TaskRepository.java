package com.sacavix.allapp.persistence.repository;

import com.sacavix.allapp.persistence.entity.Task;
import com.sacavix.allapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByTaskStatus(TaskStatus status);
    @Modifying
    @Query(value = "UPDATE Task SET Finished=true WHERE id=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);


}
