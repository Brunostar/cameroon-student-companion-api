package com.brunostar.csc.controller;

import com.brunostar.csc.model.ExamType;
import com.brunostar.csc.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/examtypes")
public class ExamTypeController {

    @Autowired
    private ExamTypeService examTypeService;

    @PostMapping
    public ResponseEntity<ExamType> createExamType(@RequestBody ExamType examType) {
        return ResponseEntity.ok(examTypeService.createExamType(examType));
    }

    @GetMapping
    public ResponseEntity<List<ExamType>> getAllExamTypes() {
        return ResponseEntity.ok(examTypeService.getAllExamTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamType> getExamTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(examTypeService.getExamTypeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamType> updateExamType(@PathVariable Long id, @RequestBody ExamType examTypeDetails) {
        return ResponseEntity.ok(examTypeService.updateExamType(id, examTypeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamType(@PathVariable Long id) {
        examTypeService.deleteExamType(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/subjects")
    public ResponseEntity<ExamType> addSubjectToExamType(@PathVariable Long id, @RequestBody String subject) {
        return ResponseEntity.ok(examTypeService.addSubjectToExamType(id, subject));
    }

    @DeleteMapping("/{id}/subjects/{subject}")
    public ResponseEntity<ExamType> removeSubjectFromExamType(@PathVariable Long id, @PathVariable String subject) {
        return ResponseEntity.ok(examTypeService.removeSubjectFromExamType(id, subject));
    }
}
