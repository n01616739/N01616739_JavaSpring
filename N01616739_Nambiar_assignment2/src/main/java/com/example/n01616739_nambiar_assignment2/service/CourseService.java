package com.example.n01616739_nambiar_assignment2.service;

import com.example.n01616739_nambiar_assignment2.model.Course;
import com.example.n01616739_nambiar_assignment2.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Add a new course
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    // Delete a course by ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
