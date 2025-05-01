package com.munaf.A16_TESTING_HOMEWORK.controllers;

import com.munaf.A16_TESTING_HOMEWORK.TestContainerConfigurations;
import com.munaf.A16_TESTING_HOMEWORK.dtos.BookDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.enums.BookType;
import com.munaf.A16_TESTING_HOMEWORK.repositories.AuthorRepository;
import com.munaf.A16_TESTING_HOMEWORK.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;


@Import(TestContainerConfigurations.class)
@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = Book.builder()
                .name("TEST")
                .price(100)
                .bookType(BookType.TECHNOLOGY)
                .build();

    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    Author createAuthor() {
        return Author.builder()
                .name("Munaf")
                .email("munaf@gmail.com")
                .age(20)
                .build();
    }

    @Test
    void testCreateBook() {
        webTestClient.post()
                .uri("/api/books")
                .bodyValue(BookDTO.bookToBookDTO(testBook))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDTO.class)
                .value(bookDTO -> {
                    Assertions.assertThat(bookDTO.getId()).isNotNull();
                    Assertions.assertThat(bookDTO.getName()).isEqualTo(testBook.getName());
                });
    }

    @Test
    void testGetBookById_whenBookIdExists_thenReturnBookDTO() {
        bookRepository.save(testBook);

        webTestClient.get()
                .uri("/api/books/{id}", testBook.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo(testBook.getName());

    }

    @Test
    void testGetBookById_whenBookIdNotExists_thenThrowException() {

        webTestClient.get()
                .uri("/api/books/{id}", 999L)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(response -> {
                    Assertions.assertThat(response).isEqualTo("Book not found with id: " + 999L);
                });

    }


    @Test
    void testUpdateBook_whenBookIdExists_thenReturnUpdatedBookDTO() {
        Book savedBook = bookRepository.save(testBook);
        testBook.setName("CHANGED NAME");

        webTestClient.put()
                .uri("api/books/{id}", savedBook.getId())
                .bodyValue(BookDTO.bookToBookDTO(testBook))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDTO.class)
                .value(updatedBook -> {
                    Assertions.assertThat(updatedBook.getName()).isEqualTo("CHANGED NAME");
                });
    }


    @Test
    void testAssignAuthorToBook_whenAuthorIdAndBookIdExists_thenAssignAuthorToBookAndReturnBookDTO() {
        bookRepository.save(testBook);
        authorRepository.save(createAuthor());

        webTestClient.put()
                .uri("/api/books/{bookId}/assignAuthorToBook/{authorId}", 1L, 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDTO.class)
                .value(bookDTO -> {
                    Assertions.assertThat(bookDTO.getAuthorName()).isEqualTo("Munaf");
                });
    }


}