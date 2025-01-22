package com.brunostar.csc.controller;

import com.brunostar.csc.model.File;
import com.brunostar.csc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<File> uploadFile(@RequestBody FileUploadRequest request) {
        try {
            File savedFile = fileService.storeFile(request.getFileUrl(), request.getUserId(),
                    request.getExamTypeId(), request.getSubject(),
                    request.getYear(), request.getPaper());
            return ResponseEntity.ok(savedFile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to store file URL", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFile(@PathVariable Long id) {
        File file = fileService.getFile(id);
        return ResponseEntity.ok(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        try {
            fileService.deleteFile(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete file", e);
        }
    }
}

// DTO for file upload request
class FileUploadRequest {
    private String fileUrl;
    private Long userId;
    private Long examTypeId;
    private String subject;
    private String year;
    private int paper;

    // Getters and Setters
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(Long examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }
}
