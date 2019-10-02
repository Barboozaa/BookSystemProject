package com.company.notequeueconsumer.util.feign;

import com.company.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "note-service")
public interface NoteClient {
    @PostMapping(value = "/notes")
    Note addNote(Note note);
    @PutMapping(value = "/notes")
    void updateNote(Note note);
}
