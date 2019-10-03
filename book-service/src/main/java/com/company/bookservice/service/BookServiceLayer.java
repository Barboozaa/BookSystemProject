package com.company.bookservice.service;

import com.company.bookservice.dao.BookDao;
import com.company.bookservice.dto.Book;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import com.company.bookservice.viewModel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceLayer {

    private final RabbitTemplate rabbitTemplate;
    private final BookDao bookDao;
    private final NoteClient client;

    public static final String EXCHANGE =
            "note-exchange";
    public static final String ROUTING_KEY =
            "note.controller";

    @Autowired
    public BookServiceLayer(BookDao bookDao, RabbitTemplate rabbitTemplate, NoteClient client) {
        this.rabbitTemplate = rabbitTemplate;
        this.bookDao = bookDao;
        this.client = client;

    }

    public BookViewModel findById(Integer id) {

        Optional<Book> book = bookDao.findById(id);
        BookViewModel model = new BookViewModel();

        if (book.isPresent()) {
            model.setBook(book.get());
            model.setNotes(client.getAllNotesByBook(model.getBook().getBookId()));
        } else {
            throw new EmptyResultDataAccessException("Nothing found for that id", 1);
        }

        return model;
    }

    public BookViewModel saveBook(BookViewModel bookViewModel) {

        Book book = bookViewModel.getBook();
        book = bookDao.save(book);
        bookViewModel.setBook(book);
        if (bookViewModel.getNote() != null) {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, bookViewModel.getNote());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bookViewModel.setNotes(client.getAllNotesByBook(bookViewModel.getBook().getBookId()));

        return bookViewModel;

    }

    public List<BookViewModel> findAllBooks() {

        List<BookViewModel> models = new ArrayList<>();
        List<Book> books = bookDao.findAll();

        books.forEach(book -> {

            BookViewModel model = new BookViewModel();
            List<Note> notes = client.getAllNotesByBook(book.getBookId());
            model.setBook(book);
            model.setNotes(notes);
        });

        return models;

    }

    public void updateBook(BookViewModel bookViewModel) {
        Book book = bookViewModel.getBook();
        bookDao.save(book);

        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, bookViewModel.getNote());
    }


    public void deleteBook(Integer id) {

        List<Note> notes = client.getAllNotesByBook(id);

        notes.forEach(note -> {
            note.setBookId(0);
            client.deleteAllNotesById(id);
        });

        bookDao.deleteById(id);

    }

}
