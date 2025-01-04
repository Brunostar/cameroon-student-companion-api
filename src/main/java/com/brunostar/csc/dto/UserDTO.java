package com.brunostar.csc.dto;

import com.brunostar.csc.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
}
