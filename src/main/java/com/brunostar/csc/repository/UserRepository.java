package com.brunostar.csc.repository;

import com.brunostar.csc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
