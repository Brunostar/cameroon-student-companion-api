package com.brunostar.csc.dto;

import com.brunostar.csc.model.Question;
import com.brunostar.csc.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long question_id;
    private UserDTO user_dto;
    private String title;
    private String content;
    private String subject;
    private String exam_type;
    private String created_at;
    private String updated_at;

    // Constructor
    public QuestionDTO() {
    }

    // Constructor to initialize from Question entity
    public QuestionDTO(Question question) {
        this.question_id = question.getId();
        this.user_dto = entityToUserDTO(question.getUser()); // Assuming there's a getUser method in Question
        this.title = question.getTitle();
        this.content = question.getContent();
        this.subject = question.getSubject();
        this.exam_type = question.getExam_type();
        this.created_at = question.getCreated_at().toString(); // Or format this according to your needs
        this.updated_at = question.getUpdated_at().toString(); // Same as above
    }

    private UserDTO entityToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUser_id());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUser_dto() {
        return user_dto;
    }

    public void setUser_dto(UserDTO user_dto) {
        this.user_dto = user_dto;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }
}
