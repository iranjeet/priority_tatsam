package com.example.priority.repository;

import com.example.priority.domain.AssnPriorityUser;
import com.example.priority.domain.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority,Long> {

    Priority findOneByName(String name);

    Priority findOneById(Long priorityId);

}
