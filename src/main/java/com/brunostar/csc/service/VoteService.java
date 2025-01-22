package com.brunostar.csc.service;

import com.brunostar.csc.model.Answer;
import com.brunostar.csc.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer voteAnswer(Long answerId, Long userId, int voteValue) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found with id: " + answerId));

        // Assuming you don't track individual votes but just update the count:
        // Here, you might want to check if the user has already voted and how to handle that (e.g., remove previous vote before adding new one)
        answer.setVotes_count(answer.getVotes_count() + voteValue);
        return answerRepository.save(answer);
    }
}
