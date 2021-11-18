package com.example.lab1v2.model;

import java.io.Serializable;

public class Task implements Serializable {

    public Task(Long id, String name, int hours, boolean isDone) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.isDone = isDone;
    }

    public Task() {
    }

    private Long id;

    public void setId(Long value) {
        id = value;
    }

    public Long getId() {
        return id;
    }

    private String name;

    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }

    private int hours;

    public void setHours(int value) {
        hours = value;
    }

    public int getHours() {
        return hours;
    }

    private boolean isDone;

    public void setDone(boolean value) {
        isDone = value;
    }

    public boolean isDone() {
        return isDone;
    }
}
