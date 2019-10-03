package com.company.bookservice.controller;

import com.company.bookservice.service.BookServiceLayer;
import com.company.bookservice.viewModel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceLayer serviceLayer;

    @Autowired
    public BookController(BookServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel saveBook(@RequestBody @Valid BookViewModel bookViewModel) {
        return serviceLayer.saveBook(bookViewModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return serviceLayer.findAllBooks();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody @Valid BookViewModel bookViewModel) {
        serviceLayer.updateBook(bookViewModel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBookById(@PathVariable Integer id) {
        return serviceLayer.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookById(@PathVariable Integer id) {
        serviceLayer.deleteBook(id);
    }

}
