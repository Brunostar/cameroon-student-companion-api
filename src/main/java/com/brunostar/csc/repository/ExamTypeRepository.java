package com.brunostar.csc.repository;

import com.brunostar.csc.model.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {
    ExamType findByName(String name);
}
