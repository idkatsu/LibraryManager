package com.example.demo.service.impl;

import com.example.demo.dto.BookLoanDTO;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.BookLoan;
import com.example.demo.model.Reader;
import com.example.demo.repository.IBookLoanRepository;
import com.example.demo.repository.IBookRepository;
import com.example.demo.repository.IReaderRepository;
import com.example.demo.service.interfaces.IBookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookLoanService implements IBookLoanService {

    private final IBookRepository bookRepository;
    private final IReaderRepository readerRepository;
    private final IBookLoanRepository bookLoanRepository;

    @Autowired
    public BookLoanService(IBookRepository bookRepository,
                           IReaderRepository readerRepository,
                           IBookLoanRepository bookLoanRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    private BookLoanDTO mapToBookLoanDTO(BookLoan bookLoan) {
        return new BookLoanDTO(
                bookLoan.getId(),
                bookLoan.getBook().getId(),
                bookLoan.getReader().getId(),
                bookLoan.getLoanDate(),
                bookLoan.getDueDate(),
                bookLoan.getReturnDate(),
                bookLoan.getStatus()
        );
    }

    @Override
    public BookLoanDTO createLoan(BookLoanDTO loanDto) {
        Book book = bookRepository.findById(loanDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Нет доступных копий книги");
        }

        Reader reader = readerRepository.findById(loanDto.getReaderId())
                .orElseThrow(() -> new RuntimeException("Читатель не найден"));

        BookLoan loan = new BookLoan();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(loanDto.getDueDate() != null ? loanDto.getDueDate() : LocalDate.now().plusDays(14));
        loan.setStatus(BookLoan.Status.ACTIVE);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BookLoan savedLoan = bookLoanRepository.save(loan);

        return mapToBookLoanDTO(savedLoan);
    }

    @Override
    public BookLoanDTO returnLoan(Long loanId) {
        BookLoan loan = bookLoanRepository.findById(loanId)
                .orElseThrow(() -> new ResourseNotFoundException("Выдача не найдена"));

        if (loan.getReturnDate() != null) {
            throw new ConflictException("Книга уже была возвращена");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(BookLoan.Status.RETURNED);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        BookLoan updated = bookLoanRepository.save(loan);

        return mapToBookLoanDTO(updated);
    }

    @Override
    public List<BookLoanDTO> getLoansByReader(Long readerId) {
        return bookLoanRepository.findByReader_Id(readerId)
                .stream()
                .map(this::mapToBookLoanDTO)
                .toList();
    }

    @Override
    public List<BookLoanDTO> getOverdueLoans() {
        return bookLoanRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now())
                .stream()
                .map(this::mapToBookLoanDTO)
                .toList();
    }
}
