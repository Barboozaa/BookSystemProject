package com.company.noteservice.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size
    public class NoteDto {

    @Min(0)
    private int note_id;
    @Min(0)
    private int book_id;
    @Size(min=5, max=255)
    private String note;

    public NoteDto(){};

        public NoteDto(int note_id, int book_id, String note) {
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
    }
