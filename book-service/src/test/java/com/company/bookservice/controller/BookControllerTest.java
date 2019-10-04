package com.company.bookservice.controller;

import com.company.bookservice.dto.Book;
import com.company.bookservice.service.BookServiceLayer;
import com.company.bookservice.util.messages.Note;
import com.company.bookservice.viewModel.BookViewModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookServiceLayer service;

    @MockBean
    RabbitTemplate template;

    @Before
    public void setUp() throws Exception {
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSaveBook() throws Exception {

        BookViewModel inModel = new BookViewModel();

        inModel.setBook(new Book(0, "Author", "Title"));
        inModel.setNote(new Note(null, 1, "Note about the book"));
        inModel.setNotes(null);

        BookViewModel outModel = new BookViewModel();

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, 1, "Note about the book"));
        outModel.setBook(new Book(1, "Author", "Title"));
        outModel.setNote(new Note(null, 1, "Note about the book"));

        outModel.setNotes(notes);

        String input = asJsonString(inModel);
        String output = asJsonString(outModel);

        when(service.saveBook(inModel)).thenReturn(outModel);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.bookId").exists())
                .andExpect(content().json(output));

    }

    @Test
    public void getAllBooks() throws Exception {

        BookViewModel model = new BookViewModel();

        model.setBook(new Book(1, "title", "author"));
        model.setNote(null);
        model.setNotes(null);

        BookViewModel model2 = new BookViewModel();

        model2.setBook(new Book(1, "title", "author"));
        model2.setNote(null);
        model2.setNotes(null);

        List<BookViewModel> models = new ArrayList<>();
        models.add(model);
        models.add(model2);

        when(service.findAllBooks()).thenReturn(models);

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(models)));
    }

    @Test
    public void testUpdateBook() throws Exception {

        BookViewModel model = new BookViewModel();
        model.setBook(new Book(12, "title", "author"));
        model.setNote(new Note(1, 12, "this is a note"));
        model.setNotes(new ArrayList<>());

        mockMvc.perform(put("/books")
                .content(asJsonString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    public void testGetBookById() throws Exception {

        BookViewModel model = new BookViewModel();

        model.setBook(new Book(1, "Title", "Author"));
        model.setNote(null);

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, 1, "Note about the book"));
        model.setNotes(notes);

        when(service.findById(1)).thenReturn(model);

        mockMvc.perform(get("/books/"+1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(model)));

    }

    @Test
    public void deleteBookById() throws Exception {

        mockMvc.perform(delete("/books/"+12))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}