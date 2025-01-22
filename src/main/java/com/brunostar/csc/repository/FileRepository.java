package com.brunostar.csc.repository;

import com.brunostar.csc.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    // Custom queries can be added here if needed
}
