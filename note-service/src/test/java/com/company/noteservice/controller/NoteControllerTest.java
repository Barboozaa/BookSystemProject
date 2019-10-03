package com.company.noteservice.controller;

import com.company.noteservice.Dto.Note;
import com.company.noteservice.service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
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

        mockMvc.perform(MockMvcRequestBuilders.
                get("/notes/"+12)).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new Note(12, 2, "Hello Note"))));


    }

    @Test
    public void getAllNotes() {
    }

    @Test
    public void updateNote() {
    }

    @Test
    public void deleteNote() {
    }

    @Test
    public void getAllNotesByBook() {
    }
}