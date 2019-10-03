package com.company.noteservice.controller;

import com.company.noteservice.Dto.Note;
import com.company.noteservice.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Service service;

    @Before
    public void setUp() throws Exception {
    }


    private String asJsonString(final Object object) throws Exception {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateNote() throws Exception {

//        Note note = new Note(0, 2, "Hello Note");
//        Note note2 = new Note(1, 2, "Hello Note");

        when(service.addNote(
                new Note(0, 2, "Hello Note")))
                .thenReturn(new Note(1, 2, "Hello Note"));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/notes")
                .content(asJsonString(new Note(0, 2, "Hello Note")))
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.noteId").exists());

    }

    @Test
    public void getNote() throws Exception {

        when(service.findById(12)).thenReturn(new Note(12, 2, "Hello Note"));

        mockMvc.perform(get("/notes/"+12)).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new Note(12, 2, "Hello Note"))));


    }

    @Test
    public void getAllNotes() throws Exception {

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, 2, "Hello There"));
        notes.add(new Note(2, 2, "Really Really Good"));
        when(service.findAllNotes()).thenReturn(notes);

        mockMvc.perform(get("/notes")).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(notes)));
    }

    @Test
    public void updateNote() throws Exception {

        mockMvc.perform(put("/notes")
        .content(asJsonString(new Note(12, 10, "New Note Title")))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    public void deleteNote() throws Exception {
        mockMvc.perform(delete("/notes/12")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    public void getAllNotesByBook() throws Exception {

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, 2, "Hello There"));
        notes.add(new Note(2, 2, "Really Really Good"));
        when(service.findNotesByBookId(2)).thenReturn(notes);

        mockMvc.perform(get("/notes/book/"+2)).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(notes)));

    }
}