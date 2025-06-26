package com.munaf.A16_TESTING_HOMEWORK.repositories;

import com.munaf.A16_TESTING_HOMEWORK.TestContainerConfigurations;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@DataJpaTest
@Import(TestContainerConfigurations.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = Author.builder()
                .name("Test Author")
                .email("test@example.com")
                .age(30)
                .build();
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void testExistsByEmail_whenAuthorNotExists_thenReturnFalse() {
        // Arrange
        String email = "test@example.com";

        // Act
        boolean isAuthorExists = authorRepository.existsByEmail(email);

        // Assert
        Assertions.assertThat(isAuthorExists)
                .isNotNull()
                .isFalse();
    }

    @Test
    void testExistsByEmail_whenAuthorExists_thenReturnTrue() {
        // Arrange
        authorRepository.save(testAuthor);

        // Act
        boolean isAuthorExists = authorRepository.existsByEmail("test@example.com");

        // Assert
        Assertions.assertThat(isAuthorExists)
                .isNotNull()
                .isTrue();
    }


    @Test
    void testFindByIdAndEmail_whenAuthorIdOrEmailNotExists_thenReturnEmpty() {
        // Arrange
        authorRepository.save(testAuthor);

        // Act
        Optional<Author> author = authorRepository.findByIdAndEmail(2L, "test@example.com");

        // Assert
        Assertions.assertThat(author)
                .isEmpty();

    }

    @Test
    void testFindByIdAndEmail_whenAuthorIdAndEmailExists_thenReturnAuthor() {
        // Arrange
        authorRepository.save(testAuthor);

        // Act
        Optional<Author> author = authorRepository.findByIdAndEmail(1L, "test@example.com");

        // Assert
        Assertions.assertThat(author)
                .isNotNull();

        Assertions.assertThat(author.get().getId()).isEqualTo(1L);
        Assertions.assertThat(author.get().getEmail()).isEqualTo("test@example.com");
    }

}