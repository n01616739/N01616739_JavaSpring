package com.example.assignment.service;

import com.example.assignment.entity.Student;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDate;


@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Integer, Student> students = new HashMap<>();

    public StudentServiceImpl() {
        students.put(1, new Student(1, "Sid", 22, "Male", "sid@gmail.com", "New York", LocalDate.of(2001, 5, 15)));
        students.put(2, new Student(2, "Sam", 23, "Female", "sam@gmail.com", "Los Angeles", LocalDate.of(2000, 3, 25)));
        students.put(3, new Student(3, "samuel", 21, "Male", "samuel@yahoo.com", "Chicago", LocalDate.of(2002, 7, 30)));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }
}
