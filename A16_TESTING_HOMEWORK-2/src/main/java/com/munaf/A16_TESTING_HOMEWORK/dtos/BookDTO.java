package com.munaf.A16_TESTING_HOMEWORK.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.munaf.A16_TESTING_HOMEWORK.entities.Author;
import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import com.munaf.A16_TESTING_HOMEWORK.enums.BookType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;

    private String name;

    private BookType bookType;

    private Integer price;

    private String authorName;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate publishedDate;


    public static BookDTO bookToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setBookType(book.getBookType());
        bookDTO.setPrice(book.getPrice());
        if (book.getAuthor() != null) {
            bookDTO.setAuthorName(book.getAuthor().getName());
        }
        bookDTO.setPublishedDate(book.getPublishedDate());
        return bookDTO;
    }

    public Book bookDTOToBook() {
        Book book = new Book();
        book.setId(this.getId());
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        book.setBookType(this.getBookType());
        book.setPublishedDate(this.getPublishedDate());
        return book;
    }

}
