package com.company.bookservice.util.feign;

import com.company.bookservice.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "note-service")
public interface NoteClient {
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable Integer id);
}
