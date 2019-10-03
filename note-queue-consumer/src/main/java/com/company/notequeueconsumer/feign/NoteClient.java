package com.company.notequeueconsumer.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
//
@FeignClient(name = "note-service")
@RequestMapping("/notes")
public interface NoteClient {
    @PostMapping
    Note addNote(@RequestBody Note note);
    @PutMapping(value = "/notes/")
    void updateNote(@RequestBody Note note);
//    @DeleteMapping("/notes/{id}")
//    void deleteNote(@PathVariable int id);
}
