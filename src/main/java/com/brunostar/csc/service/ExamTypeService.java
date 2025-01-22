package com.brunostar.csc.service;

import com.brunostar.csc.model.ExamType;
import com.brunostar.csc.repository.ExamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    public ExamType createExamType(ExamType examType) {
        return examTypeRepository.save(examType);
    }

    public List<ExamType> getAllExamTypes() {
        return examTypeRepository.findAll();
    }

    public ExamType getExamTypeById(Long id) {
        return examTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam type not found with id: " + id));
    }

    public ExamType updateExamType(Long id, ExamType examTypeDetails) {
        ExamType examType = getExamTypeById(id);
        examType.setName(examTypeDetails.getName());
        examType.setSubjects(examTypeDetails.getSubjects());
        return examTypeRepository.save(examType);
    }

    public void deleteExamType(Long id) {
        ExamType examType = getExamTypeById(id);
        examTypeRepository.delete(examType);
    }

    public ExamType addSubjectToExamType(Long examTypeId, String subject) {
        ExamType examType = getExamTypeById(examTypeId);
        Set<String> subjects = examType.getSubjects();
        subjects.add(subject);
        examType.setSubjects(subjects);
        return examTypeRepository.save(examType);
    }

    public ExamType removeSubjectFromExamType(Long examTypeId, String subject) {
        ExamType examType = getExamTypeById(examTypeId);
        examType.getSubjects().remove(subject);
        return examTypeRepository.save(examType);
    }
}
