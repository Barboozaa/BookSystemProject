package com.company.bookservice.controller;

import com.company.bookservice.service.BookServiceLayer;
import com.company.bookservice.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookServiceLayer serviceLayer;

    @Autowired
    public BookController(BookServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping("/books")
    public BookViewModel saveBook(BookViewModel bookViewModel) {
        return null;
    }
}
