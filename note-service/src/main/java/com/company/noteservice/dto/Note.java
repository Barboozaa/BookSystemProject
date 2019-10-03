package com.company.noteservice.Dto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private int note_id;
    @Column(name = "book_id")
    private int book_id;
    @Column(name = "note")
    @Size(min = 5, max = 255)
    private String note;

    public Note() {
    }

    ;

    public Note(int note_id, int book_id, String note) {
        this.note_id = note_id;
        this.book_id = book_id;
        this.note = note;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return note_id == note1.note_id &&
                book_id == note1.book_id &&
                Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note_id, book_id, note);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", book_id=" + book_id +
                ", note='" + note + '\'' +
                '}';
    }
}
