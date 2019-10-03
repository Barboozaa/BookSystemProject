package com.company.bookservice.util.feign;

import com.company.bookservice.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteClient {
    @GetMapping("/notes/{id}")
    Note getNoteById(@PathVariable Integer id);
    @GetMapping("/notes")
    List<Note> getAllNotes();
    @GetMapping("/notes/book/{id}")
    List<Note> getAllNotesByBook(@PathVariable Integer id);
    @DeleteMapping("/notes/{id}")
    void deleteAllNotesById(@PathVariable Integer id);
}
