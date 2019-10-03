package com.company.noteservice.service;

import com.company.noteservice.dao.NoteDaoJpaImpl;
import com.company.noteservice.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;


public class Service {


    private final NoteDaoJpaImpl NoteDao;

    public static final String EXCHANGE =
            "note-exchange";
    public static final String ROUTING_KEY =
            "note.controller";

    @Autowired
    public Service(NoteDaoJpaImpl NoteDao, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.NoteDao = NoteDao;
    }

    public Note findById(Integer id) {
        Optional<Note> book = NoteDao.findById(id);
        if (note.isPresent()) {
            return book.get();
        } else {
            throw new EmptyResultDataAccessException("Nothing found for that id", 1);
        }
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
