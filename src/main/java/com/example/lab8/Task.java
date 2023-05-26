package com.example.lab8;

public class Task {

    long id;
    String target;
    String dt;
    public Task(long id, String target, String dt) {
        this.id = id;
        this.target = target;
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", target='" + target + '\'' +
                ", localDateTime='" + dt + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
