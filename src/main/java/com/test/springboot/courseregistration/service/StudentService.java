package com.test.springboot.courseregistration.service;

import com.test.springboot.courseregistration.model.Course;
import com.test.springboot.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentService {
    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentDao.getAllStudents();
        return students;
    }

    public List<Student> getAllStudentsInCourse(UUID courseId)
    {
        Course course = new Course().id(courseId);
        List<Student> students = studentDao.getAllStudentsInCourse(course);
        return students;
    }

    public void addStudent(UUID studentId, String name, ArrayList<Course> regCourses)
    {
        Student student = new Student().id(studentId).name(name).registeredCourses(regCourses);
        studentDao.addNewStudent(student);
    }

    public void deleteStudent(UUID studentId)
    {
        Student student = new Student().id(studentId);
        studentDao.deleteStudent(student);
    }

    public List<Course> getCourses()
    {
        //TODO: implement the courseDao and get courses
        return new ArrayList<Course>();
    }

    public void addCourse(UUID courseId, String courseName)
    {
        Course course = new Course().id(courseId).name(courseName);
        //TODO: implement the courseDao and add course
    }

    public void deleteCourse(UUID courseId)
    {
        Course course = new Course().id(courseId);
        //TODO: implement the courseDao and delete course
    }
}
