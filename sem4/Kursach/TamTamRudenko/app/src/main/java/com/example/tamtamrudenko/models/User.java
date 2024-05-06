package com.example.tamtamrudenko.models;

import java.util.List;
import java.util.UUID;

public class User {
    public String id;
    public String name;
    public String surname;
    public Integer age;
    public String description;
    public List<UUID> events;
    public Boolean isCreator;

    public User(String id, String name, String surname,
                Integer age, String description,
                List<UUID> events, Boolean isCreator) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.description = description;
        this.events = events;
        this.isCreator = isCreator;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UUID> getEvents() {
        return events;
    }

    public void setEvents(List<UUID> events) {
        this.events = events;
    }

    public Boolean getCreator() {
        return isCreator;
    }

    public void setCreator(Boolean creator) {
        isCreator = creator;
    }
}
