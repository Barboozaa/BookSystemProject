package com.company.noteservice.controller;

import com.company.noteservice.Dto.Note;
import com.company.noteservice.dao.NoteDaoJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ahmedkaahin on 10/2/19.
 */
@RestController
@RequestMapping(value="notes")
public class NoteController {
    @Autowired
    NoteDaoJpaImpl dao;

    public NoteController(NoteDaoJpaImpl dao){
      this.dao = dao;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNote(@RequestBody Note note){
        System.out.println("Note recieved");
    }
}

//    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public Note getNote(@PathVariable int id) {
//        return dao.getNote(id);
//    }
//
//    @RequestMapping(value = "/note", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Note> getAllNotes() {
//        return dao.getAllNotes();
//    }
//
//    @RequestMapping(value = "/note", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void updateNote(@RequestBody Note  note)  {
//        dao.updateNote(note);
//    }
//
//    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteNote(@PathVariable int id) {
//        dao.deleteNote(id);
//    }

//}
