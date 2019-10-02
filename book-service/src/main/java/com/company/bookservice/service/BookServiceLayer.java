package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceLayer {
    private final BookDao bookDao;

    @Autowired
    public BookServiceLayer(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book findById(Integer id){
        Optional<Book> book = bookDao.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new EmptyResultDataAccessException("Nothing found for that id", 1);
        }
    }

    public Book saveBook(Book book) {
        return bookDao.save(book);
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
