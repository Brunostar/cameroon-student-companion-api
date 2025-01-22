package com.brunostar.csc.controller;

import com.brunostar.csc.model.Answer;
import com.brunostar.csc.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/answer/{answerId}/user/{userId}")
    public ResponseEntity<Answer> voteOnAnswer(@PathVariable Long answerId,
                                               @PathVariable Long userId,
                                               @RequestParam int voteValue) {
        try {
            Answer updatedAnswer = answerService.voteAnswer(answerId, userId, voteValue);
            return ResponseEntity.ok(updatedAnswer);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("User has already voted on this answer")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing vote", e);
        }
    }
}
