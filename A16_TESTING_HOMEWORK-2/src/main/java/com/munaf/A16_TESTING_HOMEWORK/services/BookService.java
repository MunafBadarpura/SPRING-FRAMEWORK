package com.munaf.A16_TESTING_HOMEWORK.services;

import com.munaf.A16_TESTING_HOMEWORK.dtos.BookDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);

    List<BookDTO> getBooksAfterPublishedDate(LocalDate publishedDate);

    BookDTO assignAuthorToBook(Long bookId, Long authorId);
}
