package org.example.demospring.service;

import org.example.demospring.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    void getAllMovies_ShouldReturnList() {
        var movies = studentService.getAllStudents();
        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    void addStudent_ShouldAddStudent() {
        Student student = new Student();
        student.setStudName("Jamuna");
        student.setStudEmail("Jamuna@test.com");

        var startingLength = studentService.getAllStudents().size();
        studentService.saveStudent(student);

        var newLength = studentService.getAllStudents().size();
        Assertions.assertEquals(startingLength + 1, newLength);
    }


    @Test
    void deleteStudentById_ShouldDeleteStudent() {

        var studentToDelete = studentService.getAllStudents().get(0);

        studentService.deleteStudentById(studentToDelete.getSid());

        Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(studentToDelete.getSid());
        });


    }

}
