package com.company.noteservice.Dao;

import com.company.noteservice.Dto.Note;
import com.company.noteservice.dao.NoteDaoJpaImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by ahmedkaahin on 10/2/19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    @Qualifier("jpaDao")
    private NoteDaoJpaImpl noteDao;
    private Note note, note1;

    @Before
    public void setUp() throws Exception {
        noteDao.findAll().forEach(note -> {
            noteDao.deleteById(note.getNoteId());
        });

        note = new Note();
        note.setNoteId(1);
        note.setBookId(2);
        note.setNote("Test Note");

        note1 = new Note();
        note1.setNoteId(2);
        note1.setBookId(3);
        note1.setNote("Test Number 2");

    }


    @Test
    public void testAddNote() {
        note = noteDao.save(note);
        assertEquals(1, noteDao.findAll().size());
    }

    @Test
    public void testGetNote() {
        note = noteDao.save(note);
        Note notetest = noteDao.findById(note.getNoteId()).get();
        assertEquals(notetest, note);

    }

    @Test
    public void updateNote() {
        note = noteDao.save(note);
        note.setBookId(12);
        noteDao.save(note);
        Note noteTest = noteDao.findById(note.getNoteId()).get();
        assertEquals(noteTest, note);
    }

    @Test
    public void deleteNote() {
        note = noteDao.save(note);
        noteDao.deleteById(note.getNoteId());
        Note noteTest = noteDao.findById(note.getNoteId()).orElse(null);
        assertNull(noteTest);
    }

    @Test
    public void getAllNotes() {

        noteDao.save(note);
        noteDao.save(note1);
        List<Note> notes = noteDao.findAll();
        assertEquals(2, notes.size());

    }


}
