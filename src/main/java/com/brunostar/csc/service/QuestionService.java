package com.brunostar.csc.service;

import com.brunostar.csc.model.Question;
import com.brunostar.csc.repository.QuestionRepository;
import com.brunostar.csc.util.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));
    }

    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = getQuestionById(id);
        question.setTitle(questionDetails.getTitle());
        question.setContent(questionDetails.getContent());
        question.setSubject(questionDetails.getSubject());
        question.setExam_type(questionDetails.getExam_type());
        return questionRepository.save(question);
    }
}
