package com.munaf.A16_TESTING_HOMEWORK.services.impl;

import com.munaf.A16_TESTING_HOMEWORK.dtos.BookDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.InvalidInputException;
import com.munaf.A16_TESTING_HOMEWORK.exceptions.ResourceNotFoundException;
import com.munaf.A16_TESTING_HOMEWORK.repositories.AuthorRepository;
import com.munaf.A16_TESTING_HOMEWORK.repositories.BookRepository;
import com.munaf.A16_TESTING_HOMEWORK.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceIMPL implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceIMPL(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(bookDTO.bookDTOToBook());
        return BookDTO.bookToBookDTO(savedBook);
    }

    @Override
    public BookDTO getBookById(Long id) {
        return BookDTO.bookToBookDTO(bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id)));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO::bookToBookDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getBooksAfterPublishedDate(LocalDate publishedDate) {
        return bookRepository.findByPublishedDateAfter(publishedDate)
                .stream()
                .map(BookDTO::bookToBookDTO)
                .toList();
    }



    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setName(bookDTO.getName());
        book.setPrice(bookDTO.getPrice());
        book.setBookType(bookDTO.getBookType());

        Book updatedBook = bookRepository.save(book);
        return BookDTO.bookToBookDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        boolean isBookExists = bookRepository.existsById(id);
        if (!isBookExists) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }


    @Override
    public BookDTO assignAuthorToBook(Long bookId, Long authorId) {
        // step:1 find book from db
        // step:2 find author from db
        // step:3 assign author to book
        // step:4 save book
        // step:5 assign book to author
        // step:6 save author

        Book book = bookRepository.findById(bookId) // step:1 find book from db
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        Author author = authorRepository.findById(authorId) // step:2 find author from db
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        book.setAuthor(author); // step:3 assign author to book
        Book savedBook = bookRepository.save(book); // step:4 save book

        author.getBooks().add(savedBook); // step:5 assign book to author
        authorRepository.save(author); // step:6 save author

        return BookDTO.bookToBookDTO(savedBook);

    }

}
