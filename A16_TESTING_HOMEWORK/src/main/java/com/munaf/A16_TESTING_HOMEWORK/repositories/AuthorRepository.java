package com.munaf.A16_TESTING_HOMEWORK.repositories;

import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);

    Optional<Author> findByIdAndEmail(Long id, String email);
}
