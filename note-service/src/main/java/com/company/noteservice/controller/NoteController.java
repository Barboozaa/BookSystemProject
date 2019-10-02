//package com.company.noteservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by ahmedkaahin on 10/2/19.
// */
//@RestController
//@RequestMapping(value="note")
//public class NoteController {
//    @Autowired
//    NoteDao dao
//
//    public NoteController(NoteDao dao){
//      this.Dao = dao;
//
//    }
//
//    @RequestMapping(value="/note", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public NoteDao createNote( Note  note{
//        return dao.addNote(Note note);
//    }
//
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
//
//}
