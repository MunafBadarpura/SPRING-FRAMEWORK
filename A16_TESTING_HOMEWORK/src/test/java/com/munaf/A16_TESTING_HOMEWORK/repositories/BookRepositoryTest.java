package com.munaf.A16_TESTING_HOMEWORK.repositories;

import com.munaf.A16_TESTING_HOMEWORK.TestContainerConfigurations;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.enums.BookType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@Import(TestContainerConfigurations.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    public void createBooks() {
        Book book1 = Book.builder()
                .name("Book 1")
                .price(100)
                .bookType(BookType.HISTORY)
                .build();


        Book book2 = Book.builder()
                .name("Book 2")
                .price(200)
                .bookType(BookType.HISTORY)
                .build();

        bookRepository.saveAll(List.of(book1, book2));
    }


    @Test
    void testFindByPublishedDateAfter_thenReturnListOfBook() {
        // Arrange
        createBooks();

        // Act
        List<Book> books = bookRepository.findByPublishedDateAfter(LocalDate.of(2025, 1, 1));

        System.out.println(books.getFirst());
        System.out.println(books.getLast());

        // Assert
        Assertions.assertThat(books)
                .isNotEmpty()
                .hasSize(2);

        Assertions.assertThat(books.get(0).getPublishedDate()).isEqualTo(LocalDate.now());
        Assertions.assertThat(books.get(1).getPublishedDate()).isEqualTo(LocalDate.now());

    }

}