package com.company.notequeueconsumer.util.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "note-service")
public interface NoteClient {
    @PostMapping(value = "/notes")
    Note addNote(@RequestBody Note note);
    @PutMapping(value = "/notes/{id}")
    void updateNote(@RequestBody Note note, @PathVariable int id);
}
