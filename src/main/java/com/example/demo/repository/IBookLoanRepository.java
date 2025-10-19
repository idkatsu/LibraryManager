package com.example.demo.repository;

import com.example.demo.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IBookLoanRepository extends JpaRepository<BookLoan, Long> {

    List<BookLoan> findByReader_Id(Long readerId);

    List<BookLoan> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);
}
