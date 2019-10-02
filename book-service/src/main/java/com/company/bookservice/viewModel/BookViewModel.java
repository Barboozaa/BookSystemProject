package com.company.bookservice.viewModel;

import com.company.bookservice.dto.Book;
import com.company.bookservice.util.messages.Note;

import java.util.Objects;

public class BookViewModel {

    Book book;
    Note note;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return book.equals(that.book) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, note);
    }
}
