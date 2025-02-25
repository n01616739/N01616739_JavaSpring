package com.example.n01616739_nambiar_assignment2.repository;

import com.example.n01616739_nambiar_assignment2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}