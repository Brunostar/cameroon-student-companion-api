package com.brunostar.csc.service;

import com.brunostar.csc.model.Answer;
import com.brunostar.csc.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestion_Id(questionId);
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: " + id));
    }

    public void deleteAnswer(Long id) {
        Answer answer = getAnswerById(id);
        answerRepository.delete(answer);
    }

    public Answer updateAnswer(Long id, Answer answerDetails) {
        Answer answer = getAnswerById(id);
        answer.setContent(answerDetails.getContent());
        answer.setVotes_count(answerDetails.getVotes_count()); // Update vote count if necessary
        return answerRepository.save(answer);
    }

    // Method to handle voting with prevention of double voting
    public Answer voteAnswer(Long answerId, Long userId, int voteValue) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: " + answerId));

        // Use a Set to store unique user IDs who have voted
        if (answer.getVoters() == null) {
            answer.setVoters(new HashSet<>());
        }

        // Check if the user has already voted
        if (answer.getVoters().contains(userId)) {
            throw new RuntimeException("User has already voted on this answer");
        }

        // Add the user to the set of voters and update the vote count
        answer.getVoters().add(userId);
        answer.setVotes_count(answer.getVotes_count() + voteValue);

        return answerRepository.save(answer);
    }
}