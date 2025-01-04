package com.brunostar.csc.controller;

import com.brunostar.csc.dto.UserDTO;
import com.brunostar.csc.model.User;
import com.brunostar.csc.service.UserService;
import com.brunostar.csc.util.UserNotFoundException;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final String userUrl = "/api/users";

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Convert DTO to Entity, save, then convert back to DTO for response
        try {
            User user = userService.createUser(userDTOToEntity(userDTO));
            return entityToDTO(user);
        } catch (Exception e) {
            logger.error("Failed to Create user", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating User", e);
        }
    }

    @GetMapping(path = "{userUrl}")
    public List<UserDTO> getUsers() {
        try {
            List<User> users = userService.getUsers(); // Assuming userUrl is used in getUsers method
            return users.stream()
                    .map(this::entityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception, maybe log it and return an empty list or throw a custom exception
            logger.error("Failed to retrieve users", e);
            return Collections.emptyList(); // or throw a custom exception
        }
    }

    @GetMapping("{userUrl}/{userId}")
    public UserDTO getUser(@PathVariable long userId) {
        try {
            User user = userService.getUserById(userId);
            return entityToDTO(user);
        } catch (UserNotFoundException e) {
            logger.error("User not found with id: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        } catch (Exception e) {
            logger.error("Error fetching user with id: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request", e);
        }
    }

    @PutMapping("{userUrl}/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        try {
            // Fetch the existing user from the database
            User existingUser = userService.getUserById(userId);
            User updatedUser = userService.updateUser(existingUser, userDTO);
            return entityToDTO(updatedUser);
        } catch (UserNotFoundException e) {
            logger.error("User not found with id: {} for update", userId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for update", e);
        } catch (Exception e) {
            logger.error("Error updating user with id: {}", userId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user", e);
        }
    }

    @DeleteMapping("{userUrl}/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            logger.error("User not found with id: {} for deletion", userId, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error deleting user with id: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private User userDTOToEntity(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        return user;
    }

    private UserDTO entityToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUser_id());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        return dto;
    }

}
