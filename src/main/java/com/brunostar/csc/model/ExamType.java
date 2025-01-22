package com.brunostar.csc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "exam_types")
@Data
public class ExamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_type_id;

    @Column(unique = true)
    private String name;

    @ElementCollection
    private Set<String> subjects = new HashSet<>();

    // Constructors, getters, and setters

    public ExamType(Long id) {
        this.exam_type_id = id;
    }

    public ExamType(String name) {
        this.name = name;
    }

    public ExamType() {
    }

    public Long getExam_type_id() {
        return exam_type_id;
    }

    public void setExam_type_id(Long exam_type_id) {
        this.exam_type_id = exam_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<String> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(String subject) {
        this.subjects.add(subject);
    }

    // Additional methods if needed
}
