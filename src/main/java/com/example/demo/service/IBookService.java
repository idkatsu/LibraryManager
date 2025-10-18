package com.example.demo.service;

import com.example.demo.dto.BookDTO;

import java.util.List;

public interface IBookService {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);

    List<BookDTO> searchBooks(String title, String author);
}
