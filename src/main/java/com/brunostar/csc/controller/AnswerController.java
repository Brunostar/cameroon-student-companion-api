package com.brunostar.csc.controller;

import com.brunostar.csc.dto.AnswerDTO;
import com.brunostar.csc.model.Answer;
import com.brunostar.csc.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public AnswerDTO createAnswer(@RequestBody AnswerDTO answerDTO) {
        Answer answer = answerService.createAnswer(dtoToEntity(answerDTO));
        return entityToDTO(answer);
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerService.getAnswersByQuestionId(questionId).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable Long id) {
        return entityToDTO(answerService.getAnswerById(id));
    }

    @PutMapping("/{id}")
    public AnswerDTO updateAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        Answer updatedAnswer = answerService.updateAnswer(id, dtoToEntity(answerDTO));
        return entityToDTO(updatedAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting answer", e);
        }
    }

    @PostMapping("/{id}/vote")
    public AnswerDTO voteAnswer(@PathVariable Long id, @RequestParam Long user_id, @RequestParam int vote) {
        Answer answer = answerService.voteAnswer(id, user_id, vote);
        return entityToDTO(answer);
    }

    private AnswerDTO entityToDTO(Answer answer) {
        return new AnswerDTO(answer);
    }

    private Answer dtoToEntity(AnswerDTO dto) {
        Answer answer = new Answer();
        answer.setContent(dto.getContent());
        // Note: The actual setting of Question and User would depend on how you manage these associations
        return answer;
    }
}
