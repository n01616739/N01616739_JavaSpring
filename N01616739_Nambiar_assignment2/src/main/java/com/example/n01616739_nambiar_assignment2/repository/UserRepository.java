package com.example.n01616739_nambiar_assignment2.repository;
import com.example.n01616739_nambiar_assignment2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
