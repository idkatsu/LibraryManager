package com.example.demo.repository;

import com.example.demo.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReaderRepository extends JpaRepository<Reader, Long> {
}
