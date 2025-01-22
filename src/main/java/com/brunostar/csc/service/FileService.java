package com.brunostar.csc.service;

import com.brunostar.csc.model.ExamType;
import com.brunostar.csc.model.File;
import com.brunostar.csc.model.User;
import com.brunostar.csc.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    /**
     * Stores the URL of a file in the database.
     * @param fileUrl URL of the file stored in Firebase
     * @param userId ID of the user uploading the file
     * @param examTypeId ID of the exam type associated with the file
     * @param subject Subject of the file
     * @param year Year of the exam
     * @param paper Paper number of the exam
     * @return The saved File entity
     */
    public File storeFile(String fileUrl, Long userId, Long examTypeId, String subject, String year, int paper) {
        File fileEntity = new File();
        fileEntity.setUser(new User(userId)); // Assuming User is fetched or created here
        fileEntity.setExamType(new ExamType(examTypeId)); // Same for ExamType
        fileEntity.setFile_name(getFileNameFromUrl(fileUrl)); // Extract filename from URL if needed
        fileEntity.setFile_path(fileUrl); // Store the URL here instead of local path
        fileEntity.setSubject(subject);
        fileEntity.setYear(year);
        fileEntity.setPaper(paper);

        return fileRepository.save(fileEntity);
    }

    /**
     * Helper method to get filename from URL, if needed
     * @param url The URL containing the filename
     * @return The filename extracted from the URL
     */
    private String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    /**
     * Retrieves a file by its ID from the database.
     * @param id The ID of the file to retrieve
     * @return The File entity
     * @throws RuntimeException if the file is not found
     */
    public File getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    /**
     * Deletes a file from the database. Note: This does not delete from Firebase Storage.
     * @param id The ID of the file to delete
     */
    public void deleteFile(Long id) {
        File file = getFile(id);
        fileRepository.delete(file);
    }

    // If you want to handle file downloads through the backend, you might add a method like this:
    public String getFileUrl(Long id) {
        File file = getFile(id);
        return file.getFile_path(); // This is the URL stored in Firebase Storage
    }
}