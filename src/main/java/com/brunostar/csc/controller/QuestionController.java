package com.brunostar.csc.controller;

import com.brunostar.csc.dto.QuestionDTO;
import com.brunostar.csc.model.Question;
import com.brunostar.csc.service.QuestionService;
import com.brunostar.csc.service.UserService;
import com.brunostar.csc.util.QuestionNotFoundException;
import com.brunostar.csc.util.UserNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public QuestionDTO createQuestion(@RequestBody @Validated QuestionDTO questionDTO) {
        logger.info("Attempting to create a new question: {}", questionDTO);
        Question question = questionService.createQuestion(dtoToEntity(questionDTO));
        return entityToDTO(question);
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        try{
            return questionService.getAllQuestions().stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error getting Questions", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting Questions", e);
        }
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable Long id) {
        try {
            return entityToDTO(questionService.getQuestionById(id));
        } catch (QuestionNotFoundException e) {
            logger.error("Question not found with id: {} for update", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found for update", e);
        } catch (Exception e) {
            logger.error("Error updating Question with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating Question", e);
        }
    }

    @PutMapping("/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        try {
            Question updatedQuestion = questionService.updateQuestion(id, dtoToEntity(questionDTO));
            return entityToDTO(updatedQuestion);
        } catch (QuestionNotFoundException e) {
            logger.error("Question not found with id: {} for update", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found for update", e);
        } catch (Exception e) {
            logger.error("Error updating Question with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating Question", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting question", e);
        }
    }

    // Assuming you have methods to convert between DTO and Entity here
    private QuestionDTO entityToDTO(Question question) {
        return new QuestionDTO(question);
    }

    private Question dtoToEntity(QuestionDTO dto) {
        Question question = new Question();
        question.setTitle(dto.getTitle());
        question.setContent(dto.getContent());
        question.setSubject(dto.getSubject());
        question.setExam_type(dto.getExam_type());
        try {
            question.setUser(userService.getUserById(dto.getUser_dto().getUserId()));
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
        return question;
    }
}