package com.example.n01616739_nambiar_assignment2.service;

import com.example.n01616739_nambiar_assignment2.model.Student;
import com.example.n01616739_nambiar_assignment2.model.Course;
import com.example.n01616739_nambiar_assignment2.repository.StudentRepository;
import com.example.n01616739_nambiar_assignment2.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Get all students
    public List<Student> getAllStudents() {
        logger.info("Fetching all students from database.");

        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student information
    public void updateStudent(Student student) {
        student.setModifiedDate(LocalDateTime.now());
        studentRepository.save(student);
        logger.info("Updated student details: ID={}, Name={}", student.getId(), student.getName());

    }

    // Enroll student in a course
    public void enrollInCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().add(course);
            student.setModifiedDate(LocalDateTime.now());

            studentRepository.save(student);
            logger.info("Student ID {} enrolled in course {}", studentId, course.getCourseName());

        }
        else {
            logger.warn("Enrollment failed: Student ID {} or Course ID {} not found", studentId, courseId);
        }
    }

    // Drop a course for student
    public void dropCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().remove(course);
            student.setModifiedDate(LocalDateTime.now());

            studentRepository.save(student);
        }
    }

    // Soft delete a student
    public void softDeleteStudent(Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setDeleted(true);
            student.setModifiedDate(LocalDateTime.now());
            studentRepository.save(student);
        }
    }
}
