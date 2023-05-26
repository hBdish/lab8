package com.example.lab8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileCont implements ITaskDAO{

    private String filePath = "src/main/java/com/example/lab8/file.txt";

    @Override
    public ObservableList<Task> getAllTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        Scanner sc;
            try {
                sc = new Scanner(new File(filePath), "UTF-8");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] string = line.split("@");

                Task task = new Task(
                        Long.valueOf(string[0]),
                        string[1],
                        string[2]
                );
                tasks.add(task);
            }
            sc.close();

        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public void addTask(Task task) {

        String text = task.getId() + "@" + task.getTarget() + "@" + task.getDt();
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateTask(Task task) {
//        ObservableList<Task> tasks = FXCollections.observableArrayList();
//        Scanner sc;
//        try {
//            sc = new Scanner(new File(filePath), "UTF-8");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        while (sc.hasNext()) {
//            String line = sc.nextLine();
//            String[] string = line.split("@");
//
//            Task task = new Task(
//                    Long.valueOf(string[0]),
//                    string[1],
//                    string[2]
//            );
//
//            if (Integer.valueOf(string[0]) != id) {
//                tasks.add(task);
//            }
//        }
//
//        sc.close();
//        try {
//            Files.write(Paths.get(filePath), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
//        }
//        catch (IOException error) {
//            System.out.println(error);
//        }
//
//        for (Task e: tasks) {
//            String text = e.getId() + "@" + e.getTarget() + "@" + e.getDt() + "\n";
//            try {
//                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
//            }
//            catch (IOException error) {
//                System.out.println(error);
//            }
//        }

    }

    @Override
    public void deleteTask(int id) {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        Scanner sc;
        try {
            sc = new Scanner(new File(filePath), "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] string = line.split("@");

            Task task = new Task(
                    Long.valueOf(string[0]),
                    string[1],
                    string[2]
            );

            if (Integer.valueOf(string[0]) != id) {
                tasks.add(task);
            }
        }

        sc.close();
        try {
            Files.write(Paths.get(filePath), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException error) {
            System.out.println(error);
        }

        for (Task e: tasks) {
            String text = e.getId() + "@" + e.getTarget() + "@" + e.getDt() + "\n";
            try {
                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
            }
            catch (IOException error) {
                System.out.println(error);
            }
        }
    }
}
