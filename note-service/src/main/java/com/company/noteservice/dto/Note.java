package com.company.noteservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Table(name = "note")
    public class Note implements Serializable {

    @Min(0)
<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private int noteId;
    @Min(0)
    @Column(name = "book_id")
=======
    private int noteId;
    @Min(0)
>>>>>>> f59462a49e8b1240012be470215fbf08ef6b4f38
    private int bookId;
    @Size(min=5, max=255)
    @Column(name = "note")
    private String note;

<<<<<<< HEAD
    public Note() {}

    public Note(@Min(0) int noteId, @Min(0) int bookId, @Size(min = 5, max = 255) String note) {
        this.noteId = noteId;
        this.bookId = bookId;
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
        public String toString() {
            return "Note{" +
                    "noteId=" + noteId +
                    ", bookId=" + bookId +
                    ", note='" + note + '\'' +
                    '}';
=======
        public Note(@Min(0) int noteId, int bookId, String note) {
            this.noteId = noteId;
            this.bookId = bookId;
            this.note =   note;
        }

        public int getNoteId() {
            return noteId;
        }

        public void setNoteId(int noteId) {
            this.noteId = noteId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
>>>>>>> f59462a49e8b1240012be470215fbf08ef6b4f38
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
