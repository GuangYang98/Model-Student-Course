package com.test.springboot.courseregistration.service;

import com.test.springboot.courseregistration.model.Course;
import com.test.springboot.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.UUID;

public class StudentDao {
    // Table is created accoring to this sql:
    // CREATE TABLE student
    // (STUDENT_ID VARCHAR(36),
    // NAME VARCHAR2(100),
    // courses VARCHAR2(1024),
    // );
    // Registered courses are stored using JSON string in this toy example
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final RowMapper<Student> STUDENT_ROW_MAPPER =
            (rs, rowNum) ->
                    new Student()
                            .id(UUID.fromString(rs.getString("student_id")))
                            .name(rs.getString("name"))
                            .registeredCourses(jsonMapper.readValue(rs.getString("courses"), new TypeReference<List<Course>>() {});
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addNewStudent(Student student)
    {
        String sql = """
                INSERT INTO student (student_id, name, course_id, course_name)"
                VALUES (:student_id, :name, :course_id, :course_name);
                """;
        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("student_id", student.getId().toString())
                        .addValue("name", student.getName())
                        .addValue("courses",
                                jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                                        student.getRegisteredCourses()
                                )));

        jdbcTemplate.update(sql, params);
    }
    public boolean deleteStudent(Student student)
    {
        String sql = "DELETE FROM student WHERE student_id = :student_id";
        UUID studentId = student.getId();
        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("student_id", studentId.toString());

        int rowsDeleted = jdbcTemplate.update(sql, params);
        return rowsDeleted > 0;
    }
    public List<Student> getAllStudents()
    {
        String sql = "SELECT * FROM student";
        List <Student> students = jdbcTemplate.query(sql, STUDENT_ROW_MAPPER);
        return students;
    }
    public List<Student> getAllStudentsInCourse(Course course)
    {
        String sql = "SELECT * FROM student WHERE course_id = :course_id";
        UUID courseId = course.getId();
        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("course_id", courseId.toString());

        List <Student> students = jdbcTemplate.query(sql, params, STUDENT_ROW_MAPPER);
        return students;
    }
}
