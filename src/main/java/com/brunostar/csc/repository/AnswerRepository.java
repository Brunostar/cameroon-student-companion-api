package com.brunostar.csc.repository;

import com.brunostar.csc.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion_Id(Long questionId);
    // Custom queries can be added here if needed, e.g., to find answers by question
}
