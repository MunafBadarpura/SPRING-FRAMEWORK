package com.munaf.A16_TESTING_HOMEWORK.repositories;

import com.munaf.A16_TESTING_HOMEWORK.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByPublishedDateAfter(LocalDate publishedDate);
}
