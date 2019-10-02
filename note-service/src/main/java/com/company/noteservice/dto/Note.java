package com.company.noteservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
<<<<<<< HEAD
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class Note implements Serializable {
=======
import javax.validation.constraints.Size
    public class Note {
>>>>>>> 4263a197eb10e87214e41f90f09101915dd8321f

        @Min(0)
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int noteId;
        @Min(0)
        private int bookId;
        @Size(min=5, max=255)
        private String note;

<<<<<<< HEAD
        public Note(){

        }

        public Note(@Min(0) int noteId, @Min(0) int bookId, @Size(min = 5, max = 255) String note) {
            this.noteId = noteId;
            this.bookId = bookId;
=======
    public Note(){};

        public Note(int note_id, int book_id, String note) {
            this.note_id = note_id;
            this.book_id = book_id;
>>>>>>> 4263a197eb10e87214e41f90f09101915dd8321f
            this.note = note;
        }


        public int getNoteId() {
            return noteId;
        }

        public Note setNoteId(int noteId) {
            this.noteId = noteId;
            return this;
        }

        public int getBookId() {
            return bookId;
        }

        public Note setBookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public String getNote() {
            return note;
        }

        public Note setNote(String note) {
            this.note = note;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Note note1 = (Note) o;
            return noteId == note1.noteId &&
                    bookId == note1.bookId &&
                    Objects.equals(note, note1.note);
        }

        @Override
        public int hashCode() {
            return Objects.hash(noteId, bookId, note);
        }
    }
