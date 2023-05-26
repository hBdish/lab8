package com.example.lab8;

public class TaskFabrica {
    public static String BD = "База данных";
    public static String RAM = "Генератор";
    public static String FILE = "Файл";

    public static ITaskDAO createDAOtask(String type) {
        if (type.equalsIgnoreCase(BD)) {
            return new PostgreDB();
        } else if (type.equalsIgnoreCase(RAM)) {
            return new ListTaskDAO(10);
        }else if (type.equalsIgnoreCase(FILE)) {
            return new FileCont();
        } else {
            throw new IllegalArgumentException("Invalid datasource type!");
        }
    }
}
