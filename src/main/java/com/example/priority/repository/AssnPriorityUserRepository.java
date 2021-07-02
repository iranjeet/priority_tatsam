package com.example.priority.repository;

import com.example.priority.domain.AssnPriorityUser;
import com.example.priority.domain.Priority;
import com.example.priority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssnPriorityUserRepository extends JpaRepository<AssnPriorityUser,Long> {
    List<AssnPriorityUser> findByUserAndPriority(User user, Priority priority);
}
