package com.example.lab1v2.storages;

import com.example.lab1v2.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    void save (Task task);

    void deleteById(Long id);

    void setBackUp(List<Task> tasks);
}
