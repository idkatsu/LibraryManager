package com.example.demo.service;

import com.example.demo.dto.BookLoanDTO;

import java.util.List;

public interface IBookLoanService {

    BookLoanDTO createLoan(BookLoanDTO loanDto);

    BookLoanDTO returnLoan(Long loanId);

    List<BookLoanDTO> getLoansByReader(Long readerId);

    List<BookLoanDTO> getOverdueLoans();
}
