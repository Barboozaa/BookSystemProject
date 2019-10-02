package com.company.bookservice.viewModel;

import com.company.bookservice.dto.Book;
import com.company.bookservice.util.messages.Note;

import java.util.List;
import java.util.Objects;

public class BookViewModel {

    Book book;
    Note note;
    List<Note> notes;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel model = (BookViewModel) o;
        return book.equals(model.book) &&
                Objects.equals(note, model.note) &&
                Objects.equals(notes, model.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, note);
    }
}
