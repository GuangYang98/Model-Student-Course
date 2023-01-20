package com.test.springboot.courseregistration.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {
    private UUID id;
    private String name;
    private ArrayList<Course> regCourse;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student id(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public List<Course> getRegisteredCourses() {
        return this.regCourse;
    }

    public void setRegisteredCourses(ArrayList<Course> regCourse) {
        this.regCourse = regCourse;
    }

    public Student registeredCourses(ArrayList<Course> regCourse) {
        this.regCourse = regCourse;
        return this;
    }
}
