package com.example.demo.controller;

import com.example.demo.dto.BookLoanDTO;
import com.example.demo.repository.IBookLoanRepository;
import com.example.demo.service.IBookLoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

    private final IBookLoanService loanService;

    @Autowired
    public BookLoanController(IBookLoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public BookLoanDTO createLoan(@Valid @RequestBody BookLoanDTO dto) {
        return loanService.createLoan(dto);
    }

    @PutMapping("/{id}/return")
    public BookLoanDTO returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

    @GetMapping("/reader/{readerId}")
    public List<BookLoanDTO> getLoansByReader(@PathVariable Long readerId) {
        return loanService.getLoansByReader(readerId);
    }

    @GetMapping("/overdue")
    public List<BookLoanDTO> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }

}
