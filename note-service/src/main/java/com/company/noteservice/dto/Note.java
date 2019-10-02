package com.company.noteservice.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size
    public class Note {

    @Min(0)
    private int noteId;
    @Min(0)
    private int bookId;
    @Size(min=5, max=255)
    private String note;

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
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
