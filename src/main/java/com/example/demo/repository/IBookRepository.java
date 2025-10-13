package com.example.demo.repository;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    List<Book> findByTitleAndAuthor(String title, String author);
}
