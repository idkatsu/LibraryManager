package com.example.demo.service.impl;

import com.example.demo.dto.BookDTO;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.IBookRepository;
import com.example.demo.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;

    @Autowired
    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDTO mapToBookDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getISBN(),
                book.getPublicationYear(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }

    private Book mapToBook(BookDTO book) {
        return new Book(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getISBN(),
                book.getPublicationYear(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }


    @Override
    public BookDTO createBook(BookDTO bookDTO) {

        bookRepository.findByISBN(bookDTO.getISBN())
                .ifPresent(b -> {
                    throw new ConflictException("Книга с таким ISBN уже существует");
                });

        Book book = mapToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        return mapToBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToBookDTO)
                .toList();
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::mapToBookDTO)
                .orElseThrow(() -> new ResourseNotFoundException("Книги с таким id нет"));
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {

        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Книги с таким id нет"));

        bookRepository.findByISBN(bookDTO.getISBN())
                .filter(b -> !b.getId().equals(id))
                .ifPresent(b -> {
                            throw new ConflictException("Книга с таким ISBN уже существует");
                        }
                );

        Book book = mapToBook(bookDTO);
        book.setId(id);
        Book updateBook = bookRepository.save(book);
        return mapToBookDTO(updateBook);
    }

    @Override
    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new ResourseNotFoundException("Книги с таким id нет");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> searchBooks(String title, String author) {
        List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
        return books.stream()
                .map(this::mapToBookDTO)
                .toList();
    }
}
