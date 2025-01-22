package com.brunostar.csc.dto;

import com.brunostar.csc.model.Answer;
import com.brunostar.csc.model.User;
import lombok.Data;

@Data
public class AnswerDTO {
    private Long answer_id;
    private Long question_id;
    private UserDTO user_dto;
    private String content;
    private int votes_count;
    private String created_at;
    private String updated_at;

    // Constructors if needed
    public AnswerDTO() {}

    public AnswerDTO(Answer answer) {
        this.answer_id = answer.getAnswer_id();
        this.question_id = answer.getQuestion().getId();
        this.user_dto = entityToUserDTO(answer.getUser());
        this.content = answer.getContent();
        this.votes_count = answer.getVotes_count();
        this.created_at = answer.getCreated_at().toString();
        this.updated_at = answer.getUpdated_at().toString();
    }

    public AnswerDTO(Long answer_id) {
        this.answer_id = answer_id;
    }

    private UserDTO entityToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUser_id());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
