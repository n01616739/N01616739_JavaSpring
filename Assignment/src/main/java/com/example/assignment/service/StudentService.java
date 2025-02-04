package com.example.assignment.service;

import com.example.assignment.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
}
