package com.example.lab1v2.model;

import java.io.Serializable;

public class Monkey implements Serializable {

    public Monkey(Long id, String name, int weight, boolean isWild) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.isWild = isWild;
    }

    public Monkey() {
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

    private int weight;

    public void setWeight(int value) {
        weight = value;
    }

    public int getWeight() {
        return weight;
    }

    private boolean isWild;

    public void setWild(boolean value) {
        isWild = value;
    }

    public boolean isWild() {
        return isWild;
    }
}
