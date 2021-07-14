package com.example.priority.repository;

import com.example.priority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByEmail(String email);
    User findByName(String name);
    Boolean existsByName(String name);
}
