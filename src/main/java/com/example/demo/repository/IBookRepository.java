package com.example.demo.repository;


import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByISBN(String isbn);

    List<Book> findByTitleAndAuthor(String title, String author);
}
