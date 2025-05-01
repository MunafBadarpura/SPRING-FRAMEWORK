package com.munaf.A16_TESTING_HOMEWORK.controllers;

import com.munaf.A16_TESTING_HOMEWORK.dtos.BookDTO;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/after-published-date/{publishedDate}")
    public ResponseEntity<List<BookDTO>> getBooksAfterPublishedDate(@PathVariable
                                                                    @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                                    LocalDate publishedDate) {
        return ResponseEntity.ok(bookService.getBooksAfterPublishedDate(publishedDate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


    // ASSIGNING AUTHOR TO BOOK
    @PutMapping("{bookId}/assignAuthorToBook/{authorId}")
    public ResponseEntity<BookDTO> assignAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.assignAuthorToBook(bookId, authorId));
    }


}
