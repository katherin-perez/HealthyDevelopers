package com.example.healthydevelopers.model;

public class Habit {
    private int id;
    private String description, title, category;

    public Habit(String description, String name) {
        this.description = description;
        this.title = name;
    }

    public Habit(String description, String name, String category) {
        this.description = description;
        this.title = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
