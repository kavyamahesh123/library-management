package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	boolean existsByEmail(String email);
}
