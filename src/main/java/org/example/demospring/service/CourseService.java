package org.example.demospring.service;

import org.example.demospring.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    void saveCourse(Course course);
    Course getCourseById(long id);
    void deleteCoursebyId(long id);
    Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
