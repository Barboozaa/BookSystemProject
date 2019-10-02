package com.company.bookservice.dao;

import com.company.bookservice.dto.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    Book book, book2, book3;

    @Before
    public void setUp() throws Exception {

        bookDao.findAll().forEach(books -> {
            bookDao.deleteById(books.getBookId());
        });

        book = new Book();
        book.setAuthor("author");
        book.setTitle("title");

        book2 = new Book();
        book2.setTitle("title2");
        book2.setAuthor("author2");

        book3 = new Book();
        book3.setTitle("title3");
        book3.setAuthor("author");

    }

    @Test
    public void testAddAndGetMethod() {
        book = bookDao.save(book);
        Book testCase = bookDao.findById(book.getBookId()).get();
        assertEquals(book, testCase);
    }

    @Test
    public void testDeleteMethod() {
        book = bookDao.save(book);
        bookDao.deleteById(book.getBookId());
        Book testCase = bookDao.findById(book.getBookId()).orElse(null);
        assertNull(testCase);
    }

    @Test
    public void testUpdateMethod() {
        book = bookDao.save(book);
        book.setTitle("new Title");
        bookDao.save(book);
        Book testCase = bookDao.findById(book.getBookId()).orElse(null);
        assertEquals(book, testCase);
    }

    @Test
    public void testGetAllBooks() {
        bookDao.save(book);
        bookDao.save(book2);
        bookDao.save(book3);

        assertEquals(3, bookDao.findAll().size());
    }

}