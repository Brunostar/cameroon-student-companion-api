package com.brunostar.csc.dto;

import com.brunostar.csc.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
