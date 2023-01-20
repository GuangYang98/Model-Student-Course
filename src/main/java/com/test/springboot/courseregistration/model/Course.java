package com.test.springboot.courseregistration.model;

import java.util.UUID;

public class Course {
    private UUID id;
    private String name;
    public Course() {
        this.id = null;
        this.name = null;
    }
    public Course(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Course id(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }
}
