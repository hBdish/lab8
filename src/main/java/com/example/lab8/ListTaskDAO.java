package com.example.lab8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ListTaskDAO implements ITaskDAO{

    private ObservableList<Task> tasks;
    Date date = new Date();
    public ListTaskDAO(int size) {
        tasks = FXCollections.observableArrayList();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    i + 1,
                    "Task " + (i + 1),
                    randomTime(random)
            );
            tasks.add(i, task);
        }
    }

    private String randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        int mounth = random.nextInt(12);
        int day = random.nextInt(28);

        String str = String.format("%04d-%02d-02 %02d:%02d", date.getYear() + 1900, mounth, day, hours, minutes);

        return str;
    }

    @Override
    public ObservableList<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return;
            }
        }
    }

    @Override
    public void deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return;
            }
        }
    }
}
