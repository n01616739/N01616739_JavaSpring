package com.example.n01616739_nambiar_assignment2.service;

import com.example.n01616739_nambiar_assignment2.model.Course;
import com.example.n01616739_nambiar_assignment2.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses from database.");
        return courseRepository.findAll();
    }

    // Add a new course
    public void addCourse(Course course) {
        courseRepository.save(course);
        logger.info("Added new course: {}", course.getCourseName());
    }

    // Delete a course by ID
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            logger.info("Deleted course: {}", course.get().getCourseName());
        } else {
            logger.warn("Failed to delete: Course with ID {} not found", id);
        }
    }
}
