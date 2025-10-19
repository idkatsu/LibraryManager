package com.example.demo.dto;

import com.example.demo.model.BookLoan;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLoanDTO {

    private Long id;

    @NotNull(message = "ID книги обязателен")
    private Long bookId;

    @NotNull(message = "ID читателя обязателен")
    private Long readerId;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BookLoan.Status status;
}
