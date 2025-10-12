package com.example.demo.service;

import com.example.demo.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    private final IBookRepository iBookRepository;

    @Autowired
    public BookService(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }
}
