package com.company.noteservice.service;

import com.company.noteservice.dao.NoteDaoJpaImpl;
import com.company.noteservice.Dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Service {


    @Autowired
    private final NoteDaoJpaImpl noteDao;

    public static final String EXCHANGE =
            "note-exchange";
    public static final String ROUTING_KEY =
            "note.controller";


    public Service(NoteDaoJpaImpl noteDao) {

        this.noteDao = noteDao;
    }

    public Note addNote(Note note) {return noteDao.save(note);}

    public Note findById(Integer id) {
        Optional<Note> note = noteDao.findById(id);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new EmptyResultDataAccessException("Nothing found for that id", 1);
        }
    }


    public List<Note> findAllNotes() {
        return noteDao.findAll();
    }

    public void updateNote(Note note) {
        noteDao.save(note);
    }

    public void deleteNote(Integer id) {
        noteDao.deleteById(id);
    }

}
