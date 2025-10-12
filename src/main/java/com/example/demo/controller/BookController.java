package com.example.demo.controller;

import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final IBookService iBookService;

    @Autowired
    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }




}
