package com.munaf.A16_TESTING_HOMEWORK.services.impl;

import com.munaf.A16_TESTING_HOMEWORK.dtos.AuthorDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.InvalidInputException;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.ResourceNotFoundException;
import com.munaf.A16_TESTING_HOMEWORK.repositories.AuthorRepository;
import com.munaf.A16_TESTING_HOMEWORK.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorServiceIMPL implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceIMPL(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        boolean isAuthorExists = authorRepository.existsByEmail(authorDTO.getEmail());
        if (isAuthorExists) {
            throw new InvalidInputException("Author with email " + authorDTO.getEmail() + " already exists");
        }

        Author savedAuthor = authorRepository.save(authorDTO.authorDTOToAuthor());
        return AuthorDTO.authorToAuthorDTO(savedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        return AuthorDTO.authorToAuthorDTO(authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id)));
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> AuthorDTO.authorToAuthorDTO(author))
                .toList();
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findByIdAndEmail(id,authorDTO.getEmail()).orElse(null);
        if (author == null) {
            throw new ResourceNotFoundException("Author not found");
        }

        if (!Objects.equals(author.getEmail(), authorDTO.getEmail())) {
            throw new InvalidInputException("Author email cannot be changed");
        }

        author.setName(authorDTO.getName());
        author.setAge(authorDTO.getAge());
        Author savedAuthor = authorRepository.save(author);
        return AuthorDTO.authorToAuthorDTO(savedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        boolean isAuthorExists = authorRepository.existsById(id);
        if (!isAuthorExists) {
            throw new InvalidInputException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
