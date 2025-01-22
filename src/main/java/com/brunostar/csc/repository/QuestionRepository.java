package com.brunostar.csc.repository;

import com.brunostar.csc.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // You can add custom query methods here if needed
}
