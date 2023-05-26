package com.example.lab8;

import javafx.collections.ObservableList;

import java.util.List;

public interface ITaskDAO {
    ObservableList<Task> getAllTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);
}
