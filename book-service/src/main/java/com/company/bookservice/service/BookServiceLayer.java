package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dto.Book;
import com.company.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceLayer {

    private final RabbitTemplate rabbitTemplate;
    private final BookDao bookDao;

    public static final String EXCHANGE =
            "note-exchange";
    public static final String ROUTING_KEY =
            "note.controller";

    @Autowired
    public BookServiceLayer(BookDao bookDao, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.bookDao = bookDao;
    }

    public Book findById(Integer id) {
        Optional<Book> book = bookDao.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new EmptyResultDataAccessException("Nothing found for that id", 1);
        }
    }

    public BookViewModel saveBook(BookViewModel bookViewModel) {
        Book book = bookViewModel.getBook();
        book = bookDao.save(book);
        bookViewModel.setBook(book);

        return bookViewModel;
    }

    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    public void updateBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Integer id) {
        bookDao.deleteById(id);
    }

}
