package com.munaf.A16_TESTING_HOMEWORK.services;

import com.munaf.A16_TESTING_HOMEWORK.dtos.AuthorDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> getAllAuthors();
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
}
