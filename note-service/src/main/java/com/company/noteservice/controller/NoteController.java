package com.company.noteservice.controller;

import com.company.noteservice.Dto.Note;
import com.company.noteservice.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Created by ahmedkaahin on 10/2/19.
 */
@RestController
public class NoteController {

    @Autowired
    private Service service;

    public NoteController(Service service){
      this.service = service;
    }

    @RequestMapping(value="/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody @Valid Note note){

        return service.addNote(note);

    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes() {
        return service.findAllNotes();
    }

    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(@RequestBody @Valid Note note) {
        service.updateNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable int id) {
        service.deleteNote(id);
    }

    @GetMapping("/notes/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotesByBook(@PathVariable Integer id) {
        return service.findNotesByBookId(id);
    }

}
