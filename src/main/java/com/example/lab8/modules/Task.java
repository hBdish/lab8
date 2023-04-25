package com.example.lab8.modules;

import java.time.LocalDateTime;

public class Task {

    long id;
    String target;

    LocalDateTime localDateTime;
    public Task(long id, String target, LocalDateTime localDateTime) {
        this.id = id;
        this.target = target;
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", target='" + target + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
