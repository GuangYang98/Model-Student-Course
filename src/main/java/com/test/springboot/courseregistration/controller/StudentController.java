package com.test.springboot.courseregistration.controller;

import com.test.springboot.courseregistration.model.Course;
import com.test.springboot.courseregistration.model.Student;
import com.test.springboot.courseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping()
    public List<Course> getCourses() {
        return studentService.getCourses();
    }

    @GetMapping("/{courseId}")
    public List<Student> getStudentInCourse(@PathVariable UUID courseId)
    {
        return studentService.getAllStudentsInCourse(courseId);
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<Void> addStudentToCourse(@RequestBody UUID studentId, @PathVariable UUID courseID)
    {
        ArrayList<Course> courses = new ArrayList<Course>(Arrays.asList(new Course().id(courseID)));
        studentService.addStudent(studentId, "Student", courses);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/student/{id}")
                .buildAndExpand(studentId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
