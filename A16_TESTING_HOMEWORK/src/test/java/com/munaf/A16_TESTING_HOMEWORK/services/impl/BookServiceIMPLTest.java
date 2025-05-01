package com.munaf.A16_TESTING_HOMEWORK.services.impl;

import com.munaf.A16_TESTING_HOMEWORK.dtos.BookDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.enums.BookType;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.ResourceNotFoundException;
import com.munaf.A16_TESTING_HOMEWORK.repositories.AuthorRepository;
import com.munaf.A16_TESTING_HOMEWORK.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceIMPLTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceIMPL bookServiceIMPL;

    private Book mockedBook;

    private Book mockedBook2;

    @BeforeEach
    void setUp() {
        mockedBook = Book.builder()
                .id(1L)
                .name("Test Book")
                .price(100)
                .bookType(BookType.TECHNOLOGY)
                .publishedDate(LocalDate.now())
                .build();

        mockedBook2 = Book.builder()
                .id(2L)
                .name("Test Book 2")
                .price(200)
                .bookType(BookType.TECHNOLOGY)
                .publishedDate(LocalDate.now())
                .build();

    }

    Author getAuthor() {
        Author mockedAuthor = Author.builder()
                .id(1L)
                .name("Munaf")
                .email("munaf@gmail.com")
                .age(20)
                .books(new ArrayList<>())
                .registerDate(LocalDate.now())
                .build();

        return mockedAuthor;
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @Test
    void testCreateBook_thenReturnBookDTO() {
        // Arrange
        BookDTO bookDTO = BookDTO.bookToBookDTO(mockedBook);
        Mockito.when(bookRepository.save(mockedBook)).thenReturn(mockedBook);

        // Act
        BookDTO createdBook = bookServiceIMPL.createBook(bookDTO);

        // Assert
        Assertions.assertThat(createdBook)
                .isNotNull()
                .isEqualTo(bookDTO);

        Mockito.verify(bookRepository).save(mockedBook);
    }

    @Test
    void testGetAllBooks_thenReturnListOfBooks() {
        // Arrange
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(mockedBook,mockedBook2));

        // Act
        List<BookDTO> savedBookDTOS = bookServiceIMPL.getAllBooks();

        // Assert
        Assertions.assertThat(savedBookDTOS)
                .isNotEmpty()
                .hasSize(2)
                .isInstanceOf(List.class);

        Mockito.verify(bookRepository, Mockito.atLeastOnce()).findAll();

    }

    @Test
    void testGetBooksAfterPublishedDate_thenReturnListOfBooks() {
        // Arrange
        LocalDate publishedDate = LocalDate.of(2025,1,1);
        Mockito.when(bookRepository.findByPublishedDateAfter(publishedDate))
                .thenReturn(List.of(mockedBook,mockedBook2));

        // Act
        List<BookDTO> savedBookDTOS = bookServiceIMPL.getBooksAfterPublishedDate(publishedDate);

        // Assert
        Assertions.assertThat(savedBookDTOS)
                .isNotEmpty()
                .hasSize(2)
                .isInstanceOf(List.class);

        Mockito.verify(bookRepository, Mockito.atLeastOnce()).findByPublishedDateAfter(publishedDate);
    }

    @Test
    void testGetBookById_whenBookIdExists_thenReturnBookDTO() {
        // Arrange
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(mockedBook));

        // Act
        BookDTO bookDTO = bookServiceIMPL.getBookById(id);

        // Assert
        Assertions.assertThat(bookDTO)
                .isNotNull()
                .isInstanceOf(BookDTO.class);

        Assertions.assertThat(bookDTO.getName()).isEqualTo(mockedBook.getName());

        Mockito.verify(bookRepository, Mockito.atLeastOnce()).findById(id);
    }

    @Test
    void testGetBookById_whenBookIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Long id = 999L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> bookServiceIMPL.getBookById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Book not found with id: " + id);
    }

    @Test
    void testUpdateBook_whenBookIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Long id = 999L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> bookServiceIMPL.updateBook(id, BookDTO.bookToBookDTO(mockedBook)))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Book not found with id: " + id);
    }

    @Test
    void testUpdateBook__whenBookIdExists_thenReturnUpdatedBookDto() {
        // Arrange
        Long id = 1L;
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(mockedBook));
        mockedBook.setName("CHANGED NAME");

        Mockito.when(bookRepository.save(mockedBook)).thenReturn(mockedBook);

        // Act
        BookDTO updatedBookDto = bookServiceIMPL.updateBook(id, BookDTO.bookToBookDTO(mockedBook));

        // Assert
        Assertions.assertThat(updatedBookDto)
                .isNotNull();

        Assertions.assertThat(updatedBookDto.getName()).isEqualTo("CHANGED NAME");

    }

    @Test
    void testDeleteBook_whenBookIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Long id = 999L;
        Mockito.when(bookRepository.existsById(id)).thenReturn(false);

        // Act and Assert
        Assertions.assertThatThrownBy(() -> bookServiceIMPL.deleteBook(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Book not found with id: " + id);

        Mockito.verify(bookRepository, Mockito.never()).deleteById(id);
    }

    @Test
    void testDeleteBook_whenBookIdNot_thenDeleteBook() {
        // Arrange
        Long id = 1L;
        Mockito.when(bookRepository.existsById(id)).thenReturn(true);

        // Act and Assert
        Assertions.assertThatCode(() -> bookServiceIMPL.deleteBook(id))
                .doesNotThrowAnyException();

        Mockito.verify(bookRepository).deleteById(id);

    }

    @Test
    void testAssignAuthorToBook_whenBookIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Long bookId = 999L;
        Long authorId = 999L;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> bookServiceIMPL.assignAuthorToBook(bookId, authorId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Book not found with id: " + bookId);
    }

    @Test
    void testAssignAuthorToBook_whenAuthorIdNotExists_thenThrowResourceNotFoundException() {
        // Arrange
        Long bookId = 1L;
        Long authorId = 999L;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockedBook));
        Mockito.when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThatThrownBy(() -> bookServiceIMPL.assignAuthorToBook(bookId, authorId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Author not found with id: " + authorId);
    }

    @Test
    void testAssignAuthorToBook_whenAuthorIdAndBookIdExists_thenAssignAuthorToBook() {
        // Arrange
        Long bookId = 1L;
        Long authorId = 1L;
        Author mockedAuthor = getAuthor();
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockedBook));
        Mockito.when(authorRepository.findById(authorId)).thenReturn(Optional.of(mockedAuthor));

        mockedBook.setAuthor(mockedAuthor);
        Mockito.when(bookRepository.save(mockedBook)).thenReturn(mockedBook);

        mockedAuthor.getBooks().add(mockedBook);
        Mockito.when(authorRepository.save(mockedAuthor)).thenReturn(mockedAuthor);


        // Act
        BookDTO assignedBook = bookServiceIMPL.assignAuthorToBook(bookId, authorId);

        // Assert
        Assertions.assertThat(assignedBook)
                .isNotNull()
                .isInstanceOf(BookDTO.class);

        Assertions.assertThat(assignedBook.getName()).isEqualTo(mockedBook.getName());
        Assertions.assertThat(assignedBook.getAuthorName()).isEqualTo(mockedAuthor.getName());


    }

}