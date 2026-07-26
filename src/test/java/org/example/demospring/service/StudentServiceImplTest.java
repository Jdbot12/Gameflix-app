package org.example.demospring.service;

import org.example.demospring.model.Student;
import org.example.demospring.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentServiceImplTest {
    @Autowired
    private StudentRepository repository;
    @Test
    void getAllStudents() {
        List<Student> items = repository.findAll();
        assertEquals(1,items.size());
    }

}
