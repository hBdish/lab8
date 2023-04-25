package com.example.lab8.modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ListTask {
    private List<Task> tasks;

    LocalDateTime datetime1 = LocalDateTime.of(2017, 1, 14, 10, 34);
    public ListTask(int size) {
        tasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    i + 1,
                    "Task " + (i + 1),
                    randomTime(random)
            );
            tasks.add(task);
        }
    }

    private LocalDateTime randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        Date date = new Date();
        LocalDateTime time;
        time = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), hours, minutes  );

        return time;
    }


    public List<Task> getTasks() {
        return tasks;
    }
}
