package com.munaf.A16_TESTING_HOMEWORK.dtos;

import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDTO {

    private Long id;
    @NotNull(message = "name should not null")
    @Size(min = 3, max = 10, message = "name should in the range if 3 to 10")
    private String name;
    @Email(message = "email should be valid")
    private String email;
    @NotNull(message = "age should not null")
    @Min(value = 15, message = "age should be at least 15")
    @Max(value = 100, message = "age should be at most 100")
    private Integer age;

    private List<String> books = new ArrayList<>();

    @CreationTimestamp
    private LocalDate registerDate;

    public static AuthorDTO authorToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setAge(author.getAge());
        authorDTO.setRegisterDate(author.getRegisterDate());
        authorDTO.setBooks(
                author.getBooks()
                .stream()
                .map(book -> book.getName())
                .toList());
        return authorDTO;
    }

    public Author authorDTOToAuthor() {
        Author author = new Author();
        author.setName(this.getName());
        author.setEmail(this.getEmail());
        author.setAge(this.getAge());
        return author;
    }

}
